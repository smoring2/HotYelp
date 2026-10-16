package rosseneri.security;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Per-user rate limiting filter that restricts API request frequency.
 * Uses authenticated user identity for accounting so that each user
 * has an independent quota. Protects metered upstream API spend.
 */
public class RateLimitFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(RateLimitFilter.class);

    private final int maxRequestsPerMinute;

    private final LoadingCache<String, AtomicInteger> requestCounts;

    public RateLimitFilter(int maxRequestsPerMinute) {
        this.maxRequestsPerMinute = maxRequestsPerMinute;
        this.requestCounts = CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build(new CacheLoader<String, AtomicInteger>() {
                    @Override
                    public AtomicInteger load(String key) {
                        return new AtomicInteger(0);
                    }
                });
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String userKey = resolveUserKey(request);

        try {
            AtomicInteger counter = requestCounts.get(userKey);
            int currentCount = counter.incrementAndGet();

            if (currentCount > maxRequestsPerMinute) {
                log.warn("Rate limit exceeded for user={} from remote={} on {} {}",
                        userKey, request.getRemoteAddr(), request.getMethod(), request.getRequestURI());
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Rate limit exceeded. Try again later.\"}");
                return;
            }
        } catch (ExecutionException e) {
            log.error("Rate limit cache error for user={}: {}", userKey, e.getMessage());
            // Fail open — allow the request but log the error
        }

        filterChain.doFilter(request, response);
    }

    private String resolveUserKey(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && auth.getPrincipal() instanceof String) {
            return (String) auth.getPrincipal();
        }
        // Fallback to IP for any edge case (should not happen with security config)
        return request.getRemoteAddr();
    }
}
