package com.sylph.sylph.global;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorEnum {

    RUNTIME_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "-1", "RUNTIME_EXCEPTION"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "-2", "BAD_REQUEST"),
    VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "-3", "VALIDATION_EXCEPTION"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "-4", "UNAUTHORIZED"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "-5", "INTERNAL_SERVER_ERROR"),
    NO_DATA_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "-6", "NO_DATA_EXCEPTION"),
    DATA_INTEGRITY_VIOLATION_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "-7", "DATA_INTEGRITY_VIOLATION_EXCEPTION"),
    JWT_EXCEPTION(HttpStatus.UNAUTHORIZED, "-8", "JWT_EXCEPTION");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
