package com.jumjari.zobiac.application.member.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.member.Member;
import com.jumjari.zobiac.domain.member.User;
import com.jumjari.zobiac.domain.refresh_token.RefreshToken;
import com.jumjari.zobiac.infrastructure.oauth.KakaoUserInfo;
import com.jumjari.zobiac.infrastructure.security.JwtProvider;
import com.jumjari.zobiac.infrastructure.security.LoginResult;
import com.jumjari.zobiac.infrastructure.security.RefreshTokenGenerator;

@Service
@RequiredArgsConstructor
@Transactional
public class OAuthLoginService {
    private final KakaoUserService kakao;
    private final RefreshTokenService service;
    private final JwtProvider jwt;
    private final RefreshTokenGenerator generator;

    @Value("${jwt.refresh}")
    private long refresh;

    public LoginResult login(KakaoUserInfo kakoInfo) {
        User user = kakao.findORCreate(kakoInfo);
        Member member = new Member(user);

        service.deleteAllByUser(user);

        String refreshTokenValue = generator.generate();
        LocalDateTime expiresAt = LocalDateTime.now().plusSeconds(refresh / 1000);

        RefreshToken refreshToken = RefreshToken.create(user, refreshTokenValue, expiresAt);

        service.save(refreshToken);
        String access = jwt.createAccessToken(member);
        
        return new LoginResult(access, refreshTokenValue);
    }
}