package com.sylph.bobmukja.global.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    USER("ROLE_USER","일반유저"),
    ADMIN("ROLE_ADMIN","어드민");

    private final String key;
    private final String title;
}
