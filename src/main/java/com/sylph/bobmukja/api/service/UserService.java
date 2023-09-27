package com.sylph.bobmukja.api.service;

import com.sylph.bobmukja.api.domain.entity.User;
import com.sylph.bobmukja.api.domain.repository.UserRepository;
import com.sylph.bobmukja.api.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserInfo() {
        Long id = UserUtils.getSessionId();
        if(ObjectUtils.isEmpty(id)) throw new RuntimeException("NO USERID");
        return userRepository.findById(id).orElseGet(null);
    }
}
