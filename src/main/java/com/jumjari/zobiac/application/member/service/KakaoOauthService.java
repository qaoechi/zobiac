package com.jumjari.zobiac.application.member.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.infrastructure.oauth.KakaoTokenReponse;
import com.jumjari.zobiac.infrastructure.oauth.KakaoUserInfo;
import com.jumjari.zobiac.infrastructure.security.LoginResult;

@Service
@RequiredArgsConstructor
@Transactional
public class KakaoOauthService {
    private final WebClient webClient;
    private final OAuthLoginService service;

    @Value("${kakao.client-id}")
    private String clientId;
    @Value("${kakao.redirect-uri}")
    private String redirectUri;
    @Value("${kakao.token-uri}")
    private String tokenUri;
    @Value("${kakao.client-secret}")
    private String clientSecret;

    public String getKakaoRedirectUrl() {
        String url = "https://kauth.kakao.com/oauth/authorize" +
            "?client_id=" + clientId +
            "&redirect_uri=" + redirectUri + 
            "&response_type=code" +
            "&scope=profile_nickname";

        return "redirect:" + url;
    }

    public LoginResult login(String code) {
        KakaoTokenReponse token = webClient.post()
            .uri(tokenUri)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromFormData("grant_type", "authorization_code")
                .with("client_id", clientId)
                .with("redirect_uri", redirectUri)
                .with("code", code)
                .with("client_secret", clientSecret)
            )
            .retrieve()
            .bodyToMono(KakaoTokenReponse.class)
            .block();

        KakaoUserInfo info = webClient.get()
            .uri("https://kapi.kakao.com/v2/user/me")
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token.getAccessToken())
            .retrieve()
            .bodyToMono(KakaoUserInfo.class)
            .block();
        return service.login(info);
    }
}