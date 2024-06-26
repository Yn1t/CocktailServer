package com.example.cocktailserver.security.models;


import com.example.cocktailserver.database.entities.CocktailUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
public class AuthToken extends AbstractAuthenticationToken {

    private CocktailUser principal;
    private String userId;

    public AuthToken(String userId, CocktailUser principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.userId = userId;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
