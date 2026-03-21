package com.jumjari.zobiac.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jumjari.zobiac.domain.member.entity.OauthAccount;

public interface OauthAccountRepository extends JpaRepository<OauthAccount, Long> {
    @EntityGraph(attributePaths = "user")
    Optional<OauthAccount> findByProviderAndProviderId(String provider, String providerId);
}