package com.sylph.sylph.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(RuntimeException e) {
        log.error("RuntimeException: {}", e.getMessage(), e);
        return ResponseEntity.status(ErrorEnum.RUNTIME_EXCEPTION.getStatus())
                .body(ErrorResponse.of(ErrorEnum.RUNTIME_EXCEPTION));
    }
}
