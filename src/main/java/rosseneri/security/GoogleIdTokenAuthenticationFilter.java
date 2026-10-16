package rosseneri.security;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * Filter that extracts and verifies a Google ID token from the Authorization header.
 * Validates the token's signature, issuer, audience, and expiry via Google's library.
 * Sets the Spring Security authentication context on success.
 */
public class GoogleIdTokenAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(GoogleIdTokenAuthenticationFilter.class);
    private static final String BEARER_PREFIX = "Bearer ";

    private final GoogleIdTokenVerifier verifier;

    public GoogleIdTokenAuthenticationFilter(GoogleIdTokenVerifier verifier) {
        this.verifier = verifier;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith(BEARER_PREFIX)) {
            String idTokenString = authHeader.substring(BEARER_PREFIX.length());

            try {
                GoogleIdToken idToken = verifier.verify(idTokenString);
                if (idToken != null) {
                    GoogleIdToken.Payload payload = idToken.getPayload();
                    String userId = payload.getSubject();
                    String email = payload.getEmail();
                    String identifier = email != null ? email : userId;

                    log.info("Authenticated request from user={} to {} {}",
                            identifier, request.getMethod(), request.getRequestURI());

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    identifier,
                                    null,
                                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    log.warn("Invalid Google ID token from remote={} for {} {}",
                            request.getRemoteAddr(), request.getMethod(), request.getRequestURI());
                }
            } catch (Exception e) {
                log.warn("Google ID token verification failed from remote={}: {}",
                        request.getRemoteAddr(), e.getMessage());
            }
        } else {
            log.debug("No Authorization header present for {} {} from remote={}",
                    request.getMethod(), request.getRequestURI(), request.getRemoteAddr());
        }

        filterChain.doFilter(request, response);
    }
}
