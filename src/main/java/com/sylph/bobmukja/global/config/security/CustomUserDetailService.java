package com.sylph.bobmukja.global.config.security;

import com.sylph.bobmukja.api.domain.entity.User;
import com.sylph.bobmukja.api.domain.repository.UserRepository;
import com.sylph.bobmukja.global.config.oauth2.OAuth2Attribute;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Not Found"));
        return UserPrincipal.create(user);
    }

    @Transactional
    public UserPrincipal getOrCreateUser(OAuth2Attribute oAuth2Attribute) {
        Optional<User> user = userRepository.findByUserIdAndProvider(oAuth2Attribute.getId(),oAuth2Attribute.getProvider());
        // 없을 경우 기본 회원가입 실행.
        if (user.isEmpty()) {
            User newUser = oAuth2Attribute.toEntity();
            user = Optional.of(userRepository.save(newUser));
        }
        return UserPrincipal.create(user.get(), oAuth2Attribute.convertToMap());
    }
}
