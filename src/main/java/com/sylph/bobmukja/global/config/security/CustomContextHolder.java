package com.sylph.bobmukja.global.config.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CustomContextHolder {
    public static CustomAuthenticationToken getToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // AnonymousAuthenticationToken일 경우 (AccessToken 없이 저장일어날 경우)
        if(authentication instanceof AnonymousAuthenticationToken) return null;

        return (CustomAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    }
}
