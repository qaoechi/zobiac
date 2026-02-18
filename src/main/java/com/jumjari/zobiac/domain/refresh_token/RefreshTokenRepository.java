package com.jumjari.zobiac.domain.refresh_token;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jumjari.zobiac.domain.member.User;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    void deleteAllByUser(User user);
}