package com.jumjari.zobiac.application.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.domain.member.User;
import com.jumjari.zobiac.domain.member.UserRepository;
import com.jumjari.zobiac.infrastructure.oauth.KakaoUserInfo;

@Service
@RequiredArgsConstructor
@Transactional
public class KakaoUserService {
    private final UserRepository repository;

    public User findORCreate(KakaoUserInfo kakao) {
        Long kakoId = kakao.getId();

        return repository.findByKakaoId(kakoId)
            .orElseGet(() -> {
                User user = User.create(kakoId);
                return repository.save(user);
            });
    }
}