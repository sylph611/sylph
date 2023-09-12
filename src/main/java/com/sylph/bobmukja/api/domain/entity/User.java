package com.sylph.bobmukja.api.domain.entity;

import com.sylph.bobmukja.global.base.BaseEntity;
import com.sylph.bobmukja.global.base.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    @Comment("공급자")
    private String provider;

    @Column(nullable = false, length = 100)
    @Comment("유저아이디")
    private String userId;

    @Column(nullable = true, length = 50)
    @Comment("성명")
    private String name;

    @Column(nullable = true, length = 50)
    @Comment("닉네임")
    private String nickname;

    @Column(nullable = false, length = 100)
    @Comment("이메일")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    @Comment("권한")
    private Role role;

    @Builder
    public User(Long id, String provider, String userId, String name, String nickname, String email, Role role) {
        this.id = id;
        this.provider = provider;
        this.userId = userId;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.role = role;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

}
