package com.sylph.bobmukja.global.config.oauth2;

import com.sylph.bobmukja.api.domain.repository.UserRepository;
import com.sylph.bobmukja.global.config.jwt.JwtTokenProvider;
import com.sylph.bobmukja.global.utils.CookieUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

import static com.sylph.bobmukja.global.config.oauth2.CustomOAuth2AuthorizationRequestRepository.REDIRECT_URL_PARAM_COOKIE_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomOAuth2AuthorizationRequestRepository customOAuth2AuthorizationRequestRepository;
    private final OAuth2Properties oAuth2Properties;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = this.determineTargetUrl(request, response, authentication);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        getRedirectStrategy().sendRedirect(request,response,targetUrl);
        this.customOAuth2AuthorizationRequestRepository.clearCookies(request, response);
        super.onAuthenticationSuccess(request, response, authentication);
    }

    /**
     * @param authentication 인증 완료된 결과
     * @return 인증 결과를 사용해서 access 토큰을 발급하고, 쿠키에 저장되어 있던 redirect_uri(프론트에서 적어준 것)와 합쳐서 반환.
     * 명시되지 않으면 설정파일에 명시된 default redirect url 값 적용
     */
    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        String targetUrl = CookieUtils.resolveCookie(request, REDIRECT_URL_PARAM_COOKIE_NAME)
                .map(Cookie::getValue)
                .orElse(oAuth2Properties.getDefaultRedirectUri());

        if (notAuthorized(targetUrl)) {
            targetUrl = oAuth2Properties.getDefaultRedirectUri();
        }

        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("accessToken", jwtTokenProvider.createAccessToken(authentication))
                .queryParam("refreshToken", jwtTokenProvider.createRefreshToken(authentication))
                .queryParam("expires_in", jwtTokenProvider.getExpiration())
                .build().toUriString();
    }

    private boolean notAuthorized(String redirectUrl) {
        return !redirectUrl.isBlank() &&
                !oAuth2Properties.isAuthorizedUri(redirectUrl);
    }
}
