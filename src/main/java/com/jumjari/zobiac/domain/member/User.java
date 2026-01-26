package com.jumjari.zobiac.domain.member;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "kakao_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kakao_id", nullable = false, unique = true)
    private Long kakaoId;
    @Column(name = "username", length = 30)
    private String username;
    @Column(name = "nickname", length = 30)
    private String nickname;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 15)
    private Role role;
    @Column(name = "is_blind", nullable = false)
    private boolean blind;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "refresh_token", length = 512)
    private String refreshToken;
    @Column(name = "token_expired_at")
    private LocalDateTime tokenExpiredAt;


    @PrePersist
    public void PrePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public static User create(Long kakaoId) {
        User user = new User();
        user.kakaoId = kakaoId;
        user.role = Role.ROLE_USER;
        user.blind = false;

        return user;
    }

    public void updateRefreshToken(String token, LocalDateTime expiredAt) {
        this.refreshToken = token;
        this.tokenExpiredAt = expiredAt;
    }
    public void clearToken() {
        this.refreshToken = null;
        this.tokenExpiredAt = null;
    }
}