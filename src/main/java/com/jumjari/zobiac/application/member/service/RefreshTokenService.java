package com.jumjari.zobiac.application.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jumjari.zobiac.domain.member.User;
import com.jumjari.zobiac.domain.refresh_token.RefreshToken;
import com.jumjari.zobiac.domain.refresh_token.RefreshTokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository repository;

    public void save(RefreshToken token) {
        repository.save(token);
    }
    public void deleteAllByUser(User user) {
        repository.deleteAllByUser(user);
    }
    public void deleteAllByToken(String token) {
        repository.deleteAllByToken(token);
    }
    
}