package com.sylph.bobmukja.global.config.jwt;


import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtTokenReIssuer {

    private final JwtTokenProvider tokenProvider;
    private final JwtTokenResolver tokenResolver;

//    public TokenDto reissueAccessToken(HttpServletRequest request) throws JwtException {
//        String refreshToken = tokenResolver.resolveTokenOrNull(request);
//        log.info("refreshToken : {}",refreshToken);
//        if (!tokenProvider.validate(refreshToken) || !jwtRedisUtils.isExists(refreshToken)) {
//            throw new BaseException(ExceptionEnum.JWT_EXCEPTION);
//        }
//
//        return tokenProvider.reissueAccessTokenUsing(refreshToken);
//    }
}
