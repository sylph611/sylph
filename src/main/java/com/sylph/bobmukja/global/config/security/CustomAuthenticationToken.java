package com.sylph.bobmukja.global.config.security;

import lombok.Builder;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
public class CustomAuthenticationToken extends AbstractAuthenticationToken {

    private final Long id;
    private final String userId;
    private final String name;
    private final String email;
    private final String provider;

    @Builder
    private CustomAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Long id, String userId, String name, String email, String provider) {
        super(authorities);
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.provider = provider;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

}
