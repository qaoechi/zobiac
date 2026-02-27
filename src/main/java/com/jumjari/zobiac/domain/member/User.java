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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 30)
    private String username;
    @Column(name = "student_number")
    private Integer number;
    @Column(name = "nickname", length = 30)
    private String nickname;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 15)
    private Role role;
    @Column(name = "is_blind", nullable = false)
    private boolean blind;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "profile_completed", nullable = false)
    private boolean completed;

    @PrePersist
    public void PrePersist() {
        this.createdAt = LocalDateTime.now();
        this.role = Role.ROLE_USER;
        this.blind = false;
        this.completed = false;
    }

    public static User create() {
        return new User();
    }
    
    public void completeProfile(String username, Integer number, String nickname) {
        this.username = username;
        this.nickname = nickname;
        this.number = number;
    }
}