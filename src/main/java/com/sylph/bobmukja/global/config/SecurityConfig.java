package com.sylph.bobmukja.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception  {
        httpSecurity
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()
                )
                // JWT 사용으로 STATELESS 설정.
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // OAuth2 로그인 서비스 구현
                .oauth2Login(Customizer.withDefaults())
        ;

        return httpSecurity.build();
    }
}
