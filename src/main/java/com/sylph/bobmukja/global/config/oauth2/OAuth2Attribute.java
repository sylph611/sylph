package com.sylph.bobmukja.global.config.oauth2;

import com.sylph.bobmukja.api.domain.entity.User;
import com.sylph.bobmukja.global.base.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

import static com.sylph.bobmukja.global.base.BaseConstant.SYSTEM_USER_ID;

@Getter
public class OAuth2Attribute {
    private final Map<String, Object> attributes;
    private final String attributeKey;
    private final String provider;
    private final String id;
    private final String email;
    private final String name;

    @Builder
    public OAuth2Attribute(Map<String, Object> attributes, String attributeKey, String provider, String id, String email, String name) {
        this.attributes = attributes;
        this.attributeKey = attributeKey;
        this.provider = provider;
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public static OAuth2Attribute of(String provider,
                                     String attributeKey,
                                     Map<String, Object> attributes) {
        switch (provider) {
            case "google" :
                return ofGoogle(attributeKey, attributes);
            case "naver" :
                return ofNaver("id", attributes);
            case "kakao" :
                return ofKakao("email", attributes);
            default :
                throw new RuntimeException("허가 되지 않은 provider");
        }
    }

    private static OAuth2Attribute ofGoogle(String attributeKey, Map<String, Object> attributes) {
        return OAuth2Attribute.builder()
                .provider("google")
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .id((String) attributes.get(attributeKey))
                .attributes(attributes)
                .attributeKey(attributeKey)
                .build();
    }

    private static OAuth2Attribute ofNaver(String attributeKey, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return OAuth2Attribute.builder()
                .provider("naver")
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .id((String) response.get(attributeKey))
                .attributes(attributes)
                .attributeKey(attributeKey)
                .build();
    }

    private static OAuth2Attribute ofKakao(String attributeKey, Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");
        return OAuth2Attribute.builder()
                .provider("kakao")
                .name((String) kakaoProfile.get("nickname"))
                .email((String) kakaoAccount.get("email"))
                .id((String) kakaoAccount.get(attributeKey))
                .attributes(attributes)
                .attributeKey(attributeKey)
                .build();
    }

    public User toEntity() {
        // 필요시 필드 추가.
        User user = User.builder()
                .provider(this.provider)
                .userId(this.id)
                .name(this.name)
                .email(this.email)
                .role(Role.USER)
                .build();
        user.setCreatedBy(SYSTEM_USER_ID);
        user.setUpdatedBy(SYSTEM_USER_ID);
        return user;
    }

    public Map<String, Object> convertToMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("provider", provider);
        map.put("id", id);
        map.put("key", attributeKey);
        map.put("name", name);
        map.put("email", email);
        return map;
    }
}
