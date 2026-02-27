package com.jumjari.zobiac.domain.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthAccountRepository extends JpaRepository<OauthAccount, Long> {
    Optional<OauthAccount> findByProviderAndProviderId(String provider, String providerId);
}