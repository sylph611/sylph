package com.sylph.bobmukja.global.config.jwt;


import com.sylph.bobmukja.global.config.security.CustomAuthenticationToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenResolver jwtTokenResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request
            , HttpServletResponse response
            , FilterChain filterChain) throws ServletException, IOException {

        String token = jwtTokenResolver.resolveTokenOrNull(request);

        if (SecurityContextHolder.getContext().getAuthentication() == null && StringUtils.hasText(token)) {

            if (!jwtTokenProvider.validate(token))
                throw new JwtException("Expired");

            CustomAuthenticationToken authentication = jwtTokenProvider.decode(token);

            // handle for authentication success
            successfulAuthentication(request, response, filterChain, authentication);
        }

        filterChain.doFilter(request, response);
    }

    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        authResult.setAuthenticated(true);
        ((CustomAuthenticationToken) authResult).setDetails(request.getRemoteAddr());
        SecurityContextHolder.getContext().setAuthentication(authResult);
        logger.trace("jwt token authentication success!");
    }

}
