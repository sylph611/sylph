package com.sylph.bobmukja.api.web.dto.external;

import com.sylph.bobmukja.api.domain.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;

@Data
public class UserResponse {

    @Schema(description = "USER PK")
    private Long id;

    @Schema(description = "소셜로그인 플랫폼")
    private String provider;

    @Schema(description = "유저 아이디(각 소셜로그인 플랫폼 PK) 미사용")
    private String userId;

    @Schema(description = "이름")
    private String name;

    @Schema(description = "닉네임")
    private String nickname;

    @Schema(description = "이메일")
    private String email;

    @Builder
    public UserResponse(Long id, String provider, String userId, String name, String nickname, String email) {
        this.id = id;
        this.provider = provider;
        this.userId = userId;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
    }

    public static UserResponse of(User user) {
        if(ObjectUtils.isEmpty(user)) return UserResponse.builder().build();
        return UserResponse.builder()
                .id(user.getId())
                .provider(user.getProvider())
                .userId(user.getUserId())
                .name(user.getName())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .build();
    }
}
