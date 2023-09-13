package com.sylph.bobmukja.global.base;

import com.sylph.bobmukja.global.config.security.CustomContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BaseAuditorAware implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        Optional<Long> result = Optional.empty();

        if(!ObjectUtils.isEmpty(CustomContextHolder.getToken())){
            result = Optional.of(CustomContextHolder
                    .getToken()
                    .getId());
        }

        return result;
    }
}
