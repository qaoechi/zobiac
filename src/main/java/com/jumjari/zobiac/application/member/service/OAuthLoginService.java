package com.jumjari.zobiac.application.member.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.member.Member;
import com.jumjari.zobiac.domain.member.User;
import com.jumjari.zobiac.domain.member.UserRepository;
import com.jumjari.zobiac.infrastructure.oauth.KakaoUserInfo;
import com.jumjari.zobiac.infrastructure.security.JwtProvider;
import com.jumjari.zobiac.infrastructure.security.LoginResult;
import com.jumjari.zobiac.infrastructure.security.TokenType;

@Service
@RequiredArgsConstructor
@Transactional
public class OAuthLoginService {
    private final KakaoUserService kakao;
    private final UserRepository repository;
    private final JwtProvider jwt;

    public LoginResult login(KakaoUserInfo kakoInfo) {
        User user = kakao.findORCreate(kakoInfo);
        Member member = new Member(user);

        String access = jwt.createToken(member, TokenType.ACCESS);
        String refresh = jwt.createToken(member, TokenType.REFRESH);

        user.updateRefreshToken(refresh, LocalDateTime.now().plusDays(1));
        repository.save(user);
        
        return new LoginResult(access, refresh);
    }
}