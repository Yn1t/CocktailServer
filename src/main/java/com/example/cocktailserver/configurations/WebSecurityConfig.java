package com.example.cocktailserver.configurations;

import com.example.cocktailserver.security.MainAuthFilter;
import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.DisableEncodeUrlFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

    private final MainAuthFilter mainAuthFilter;

    public WebSecurityConfig(MainAuthFilter mainAuthFilter) {
        this.mainAuthFilter = mainAuthFilter;
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("Cache-Control", "Content-Type", "x-access-login", "x-access-password"));
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT", "OPTIONS", "PATCH", "DELETE"));

        return httpSecurity
                .anonymous(AbstractHttpConfigurer::disable)         // AnonymousAuthenticationFilter
                .csrf(AbstractHttpConfigurer::disable)              // CsrfFilter
                .sessionManagement(AbstractHttpConfigurer::disable) // DisableEncodeUrlFilter, SessionManagementFilter
                .exceptionHandling(AbstractHttpConfigurer::disable) // ExceptionTranslationFilter
                .headers(AbstractHttpConfigurer::disable)           // HeaderWriterFilter
                .logout(AbstractHttpConfigurer::disable)            // LogoutFilter
                .requestCache(AbstractHttpConfigurer::disable)      // RequestCacheAwareFilter
                .servletApi(AbstractHttpConfigurer::disable)        // SecurityContextHolderAwareRequestFilter
                .securityContext(AbstractHttpConfigurer::disable)   // SecurityContextPersistenceFilter
                .build();
    }
}
