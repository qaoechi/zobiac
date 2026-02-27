package com.jumjari.zobiac.application.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jumjari.zobiac.application.member.Member;
import com.jumjari.zobiac.domain.member.User;
import com.jumjari.zobiac.domain.refresh_token.RefreshToken;
import com.jumjari.zobiac.domain.refresh_token.RefreshTokenRepository;
import com.jumjari.zobiac.infrastructure.security.JwtProvider;
import com.jumjari.zobiac.infrastructure.security.LoginResult;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository repository;
    private final JwtProvider jwt;

    public void save(RefreshToken token) {
        repository.save(token);
    }
    public void deleteAllByUser(User user) {
        repository.deleteAllByUser(user);
    }
    public void deleteAllByToken(String token) {
        repository.deleteAllByToken(token);
    }

    public LoginResult refresh(String token) {
        RefreshToken refreshToken = repository.findByToken(token)
            .orElseThrow(() -> new RuntimeException("invalid token"));

        if (refreshToken.isExpired()) {
            repository.delete(refreshToken);
            throw new RuntimeException("refresh expired");
        }

        Member member = new Member(refreshToken.getUser());
        String access = jwt.createAccessToken(member);

        return new LoginResult(access, refreshToken, !refreshToken.getUser().isCompleted());
    }

}