package com.sylph.bobmukja.global.config.oauth2;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "oauth2")
public class OAuth2Properties {
    private final List<String> redirectUris = new ArrayList<>();
    private String defaultRedirectUri;
    private String logoutUri;
    private String logoutSuccessUri;

    public boolean isAuthorizedUri(String inputUri) {
        URI redirectUri = URI.create(inputUri);
        return this.redirectUris.stream()
                .anyMatch(uri -> {
                    URI authorizedURI = URI.create(uri);
                    return authorizedURI.getHost().equalsIgnoreCase(redirectUri.getHost())
                            && authorizedURI.getPort() == redirectUri.getPort()
                            && authorizedURI.getScheme().equalsIgnoreCase(redirectUri.getScheme());
                });
    }
}
