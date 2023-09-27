package com.sylph.bobmukja.api.web.controller;

import com.sylph.bobmukja.api.service.UserService;
import com.sylph.bobmukja.api.web.dto.external.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "99. 유저 API", description = "99. 유저 API")
public class UserController {

    private final UserService userService;

    @Operation(description  = "유저 프로필")
    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getUserInfo() {
        return ResponseEntity.of(
                Optional.of(
                        UserResponse.of(userService.getUserInfo())
                )
        );
    }
}
