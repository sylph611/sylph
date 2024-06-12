package com.sylph.sylph.global;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private final String code;
    private final String message;

    @Builder
    private ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorResponse of(ErrorEnum errorEnum) {
        return ErrorResponse.builder()
                .code(errorEnum.getCode())
                .message(errorEnum.getMessage())
                .build();
    }

    public static ErrorResponse from(String code, String message) {
        return ErrorResponse.builder()
                .code(code)
                .message(message)
                .build();
    }
}
