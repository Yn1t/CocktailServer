package com.example.cocktailserver.configurations;

import com.example.cocktailserver.security.MainAuthFilter;
import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.DisableEncodeUrlFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

    private final MainAuthFilter mainAuthFilter;

    public WebSecurityConfig(MainAuthFilter mainAuthFilter) {
        this.mainAuthFilter = mainAuthFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.securityMatcher(new NegatedRequestMatcher(new AntPathRequestMatcher("/public/**")))
                .addFilterAfter(
                        (Filter) mainAuthFilter.setRequireAuthMatcher(
                                new AndRequestMatcher(new AntPathRequestMatcher("/api/private/**"))
                        ),
                        UsernamePasswordAuthenticationFilter.class
                ).build();
    }
}
