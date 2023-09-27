package com.sylph.bobmukja.api.utils;

import com.sylph.bobmukja.api.domain.repository.UserRepository;
import com.sylph.bobmukja.global.config.security.CustomAuthenticationToken;
import com.sylph.bobmukja.global.config.security.CustomContextHolder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtils {
    private final UserRepository userRepository;

    public static Long getSessionId() {
        CustomAuthenticationToken token = CustomContextHolder.getToken();
        if(ObjectUtils.isEmpty(token)) return null;
        else return token.getId();
    }
}
