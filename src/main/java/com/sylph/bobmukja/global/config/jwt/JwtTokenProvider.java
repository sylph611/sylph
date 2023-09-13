package com.sylph.bobmukja.global.config.jwt;


import com.sylph.bobmukja.global.config.security.CustomAuthenticationToken;
import com.sylph.bobmukja.global.config.security.UserPrincipal;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final Long ACCESS_TOKEN_VALID_MILLISECOND = 30 * 60 * 1000L; // 0.5 hour
    private static final Long REFRESH_TOKEN_VALID_MILLI_SECOND = 7 * 24 * 60 * 60 * 1000L; // 7 days
    private static final String PROVIDER = "provider";
    private static final String AUTHORITY = "authorities";
    private static final String EMAIL = "email";
    private static final String USER_ID = "userId";
    private static final String ID = "id";


    public boolean validate(String token) {

        try {
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    public CustomAuthenticationToken decode(String token) {
        Claims claims = this.parseClaims(token);

        String provider = claims.get(PROVIDER, String.class);
        String userId = claims.get(USER_ID, String.class);
        Long id = claims.get(ID, Long.class);
        String email = claims.get(EMAIL, String.class);
        List<? extends GrantedAuthority> grantedAuthorities =
                (List<SimpleGrantedAuthority>) claims.get(AUTHORITY, List.class).stream()
                        .map(authority-> new SimpleGrantedAuthority((String) authority))
                        .collect(Collectors.toList());
        return new CustomAuthenticationToken(grantedAuthorities, id, userId, email, provider);
    }

//    /* 토큰 body 에 넣어둔 사용자 정보를 가져옴
//     * validation 검사를 먼저 꼭 해야함! */
    private Claims parseClaims(String token) throws JwtException {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
    }
//
    public String createAccessToken(Authentication authentication) {
        return createToken(authentication, ACCESS_TOKEN_VALID_MILLISECOND);
    }

    public String createRefreshToken(Authentication authentication) {
        return this.createToken(authentication, REFRESH_TOKEN_VALID_MILLI_SECOND);
    }

    public Long getExpiration() {
        return this.ACCESS_TOKEN_VALID_MILLISECOND / 1000;
    }

    private String createToken(Authentication authentication, Long expiration) {

        assert authentication != null;
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setSubject(userPrincipal.getUserId())
                .claim(PROVIDER     , userPrincipal.getProvider())
                .claim(USER_ID      , userPrincipal.getUserId())
                .claim(ID           , userPrincipal.getId())
                .claim(EMAIL        , userPrincipal.getEmail())
                .claim(AUTHORITY    , userPrincipal.getAuthorities())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SECRET_KEY)
                .compact();
    }


//    public TokenDto reissueAccessTokenUsing(String refreshToken) throws JwtException {
//        Claims claims = this.parseClaims(refreshToken);
//        return this.createAccessTokenOnly(claims);
//    }
//
//    private TokenDto createAccessTokenOnly(Claims claims) {
//        Date now = new Date();
//
//        String accessToken = Jwts.builder()
//                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
//                .setClaims(claims)
//                .setIssuedAt(now)
//                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_MILLISECOND))
//                .signWith(SECRET_KEY)
//                .compact();
//
//        return TokenDto.builder()
//                .grantType("Bearer")
//                .accessToken(accessToken)
//                .refreshToken("")
//                .accessTokenExpireDate(ACCESS_TOKEN_VALID_MILLISECOND)
//                .build();
//    }
//
}
