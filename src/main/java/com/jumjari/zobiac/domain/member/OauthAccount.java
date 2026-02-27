package com.jumjari.zobiac.domain.member;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "oauth_accounts",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"provider", "provider_id"})
    }
)
public class OauthAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "provider", length = 15, nullable = false)
    private String provider;
    @Column(name = "provider_id", length = 100, nullable = false)
    private String providerId;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void PrePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public static OauthAccount create(User user, String provider, String providerId) {
        OauthAccount account = new OauthAccount();
        account.user = user;
        account.provider = provider;
        account.providerId = providerId;

        return account;
    }
}