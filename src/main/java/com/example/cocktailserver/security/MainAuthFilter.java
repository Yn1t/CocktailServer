package com.example.cocktailserver.security;

import com.example.cocktailserver.database.entities.CocktailUser;
import com.example.cocktailserver.database.repositories.CocktailUserRepository;

import com.example.cocktailserver.security.models.AuthToken;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class MainAuthFilter implements Filter {
    private static final String LOGIN_HEADER = "x-access-login";
    private static final String PASSWORD_HEADER = "x-access-password";

    protected final AuthenticationFailureHandler failureHandler;

    private final CocktailUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private List<RequestMatcher> requireAuthMatcher;

    public MainAuthFilter setRequireAuthMatcher(List<RequestMatcher> requireAuthMatcher) {
        this.requireAuthMatcher = requireAuthMatcher;
        return this;
    }

    public MainAuthFilter(AuthenticationFailureHandler failureHandler,
                          CocktailUserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.failureHandler = failureHandler;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext((Object) this,
                (jakarta.servlet.ServletContext) filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        if (requireAuth((HttpServletRequest) req)) {
            AuthToken token = tryAuth((HttpServletRequest) req, (HttpServletRequest) res);
            if (token == null) {
                token = new AuthToken(null, null, Collections.singleton(
                        new SimpleGrantedAuthority("USER")
                ));
            }
            SecurityContextHolder.getContext().setAuthentication(token);
            chain.doFilter(req, res);
        }
        else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {

    }

    @Nullable
    protected AuthToken tryAuth(HttpServletRequest req, HttpServletRequest res) {
        String login = req.getHeader(LOGIN_HEADER);
        String password = req.getHeader(PASSWORD_HEADER);

        Optional<CocktailUser> optionalUser = userRepository.findOptionalByEmail(login);

        if (optionalUser.isEmpty()) {
            return null;
        }

        CocktailUser user = optionalUser.get();

        if (!passwordEncoder.matches(password + "sada", user.getPassword())) {
            return null;
        }

        Collection<? extends GrantedAuthority> authorities = Collections.singleton(
                new SimpleGrantedAuthority("BASE_USER")
        );

        return new AuthToken(user.getId(), user, authorities);
    }

    private boolean requireAuth(HttpServletRequest req) {
        boolean find = requireAuthMatcher.stream().anyMatch(m -> m.matches((jakarta.servlet.http.HttpServletRequest) req));
        return requireAuthMatcher == null || find;
    }
}
