package com.jumjari.zobiac.application.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.member.Member;
import com.jumjari.zobiac.domain.member.User;
import com.jumjari.zobiac.infrastructure.oauth.KakaoUserInfo;
import com.jumjari.zobiac.infrastructure.security.JwtProvider;

@Service
@RequiredArgsConstructor
@Transactional
public class OAuthLoginService {
    private final KakaoUserService kakao;
    private final JwtProvider jwt;

    public String login(KakaoUserInfo kakoInfo) {
        User user = kakao.findORCreate(kakoInfo);
        Member member = new Member(user);
        
        return jwt.createToken(member);
    }
}