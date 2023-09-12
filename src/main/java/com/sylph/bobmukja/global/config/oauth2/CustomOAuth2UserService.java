package com.sylph.bobmukja.global.config.oauth2;

import com.sylph.bobmukja.api.domain.entity.User;
import com.sylph.bobmukja.api.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // 기본 OAuth2UserService 객체 생성
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();

        // OAuth2UserService를 사용하여 OAuth2User 정보를 가져온다.
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        // 클라이언트 등록 ID(barogo, google등)와 사용자 이름 속성을 가져온다. application yaml.
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        // OAuth2UserService를 사용하여 가져온 OAuth2User 정보로 OAuth2Attribute 객체를 만든다.
        OAuth2Attribute oAuth2Attribute =
                OAuth2Attribute.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        String userId = oAuth2Attribute.getId();
        // 가입된 회원인지 조회한다.
        Optional<User> findUser = userRepository.findByUserId(userId);
        // 없을 경우 기본 회원가입 실행.
        if (findUser.isEmpty()) {
            findUser = userSignup(oAuth2Attribute);
        }

        // 회원의 권한과, 회원속성, 속성이름을 이용해 DefaultOAuth2User 객체를 생성해 반환한다.
        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(findUser.get().getRoleKey())),
                oAuth2Attribute.convertToMap(), oAuth2Attribute.getAttributeKey());
    }

    /**
     * 미 가입 유저일 경우 유저 생성.
     * @param OAuth2Attribute
     * @return
     */
    private Optional<User> userSignup(OAuth2Attribute OAuth2Attribute){
        User user = OAuth2Attribute.toEntity();
        return Optional.of(userRepository.save(user));
    }

}
