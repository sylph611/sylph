package com.sylph.bobmukja.api.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlaceType {

    MAIN("MAIN"),
    SUB("SUB");

    private final String code;
}
