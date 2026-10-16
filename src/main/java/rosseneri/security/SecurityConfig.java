package rosseneri.security;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

/**
 * Spring Security configuration that enforces Google ID token authentication
 * on all /api/** endpoints. Static frontend resources and health checks are
 * permitted without authentication.
 *
 * Adds a rate-limiting filter after authentication so per-user accounting
 * is available.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${goyelp.google.client-id}")
    private String googleClientId;

    @Value("${goyelp.rate-limit.requests-per-minute:30}")
    private int requestsPerMinute;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Stateless API — no session needed
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // Disable CSRF for stateless API with bearer token auth
                .csrf().disable()
                // Allow CORS for SPA frontend
                .cors()
                .and()
                .authorizeRequests()
                    // Static resources and frontend routes — public
                    .antMatchers("/", "/index.html", "/static/**", "/favicon.ico",
                            "/manifest.json", "/logo*.png", "/robots.txt").permitAll()
                    // API endpoints require authentication
                    .antMatchers("/api/**").authenticated()
                    // All other requests permitted (frontend routes handled by SPA)
                    .anyRequest().permitAll()
                .and()
                // Add Google ID token verification filter before Spring Security's default auth
                .addFilterBefore(googleIdTokenAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class)
                // Add rate limiting after authentication
                .addFilterAfter(rateLimitFilter(),
                        GoogleIdTokenAuthenticationFilter.class);
    }

    @Bean
    public GoogleIdTokenVerifier googleIdTokenVerifier() {
        return new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(), JacksonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(googleClientId))
                .build();
    }

    @Bean
    public GoogleIdTokenAuthenticationFilter googleIdTokenAuthenticationFilter() {
        return new GoogleIdTokenAuthenticationFilter(googleIdTokenVerifier());
    }

    @Bean
    public RateLimitFilter rateLimitFilter() {
        return new RateLimitFilter(requestsPerMinute);
    }
}
