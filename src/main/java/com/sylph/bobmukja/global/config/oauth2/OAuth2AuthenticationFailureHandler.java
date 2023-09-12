package com.sylph.bobmukja.global.config.oauth2;

import com.sylph.bobmukja.global.utils.CookieUtils;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.sylph.bobmukja.global.config.oauth2.CustomOAuth2AuthorizationRequestRepository.REDIRECT_URL_PARAM_COOKIE_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final CustomOAuth2AuthorizationRequestRepository customOAuth2AuthorizationRequestRepository;
    private final OAuth2Properties oAuth2Properties;
    private static final String ERROR_PARAM = "?error=";
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String redirectUri = CookieUtils.resolveCookie(request, REDIRECT_URL_PARAM_COOKIE_NAME)
                .map(Cookie::getValue)
                .orElse(null);

        String targetUrl = getAuthorizedTargetUrl(exception, redirectUri);

        customOAuth2AuthorizationRequestRepository.clearCookies(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private String getAuthorizedTargetUrl(AuthenticationException exception, String redirectUri) {

        StringBuilder targetUrl = new StringBuilder();
        if (StringUtils.isEmpty(redirectUri) || redirectUri.isBlank() || notAuthorized(redirectUri)) {
            targetUrl.append(oAuth2Properties.getDefaultRedirectUri());
        }
        else {
            targetUrl.append(redirectUri);
        }
        targetUrl.append(ERROR_PARAM).append(exception.getLocalizedMessage());

        return targetUrl.toString();
    }

    private boolean notAuthorized(String redirectUrl) {
        return !redirectUrl.isBlank() &&
                !oAuth2Properties.isAuthorizedUri(redirectUrl);
    }

}
