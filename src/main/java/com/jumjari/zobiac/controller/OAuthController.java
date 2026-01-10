package com.jumjari.zobiac.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.jumjari.zobiac.infrastructure.oauth.KakaoTokenReponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {
    private final WebClient webClient;
    @Value("${kakao.client-id}")
    private String clientId;
    @Value("${kakao.redirect-uri}")
    private String redirectUri;
    @Value("${kakao.token-uri}")
    private String tokenUri;
    @Value("${kakao.client-secret}")
    private String clientSecret;

    @GetMapping("/kakao")
    public String redirectToKakao() {
        String url = "https://kauth.kakao.com/oauth/authorize" +
            "?client_id=" + clientId +
            "&redirect_uri=" + redirectUri + 
            "&response_type=code";

        return "redirect:" + url;
    }
    @GetMapping("/kakao/callback")
    @ResponseBody
    public String kakaoCallback(@RequestParam String code) {
        System.out.println(tokenUri);
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

        System.out.println("access token = " + token.getAccessToken());
        return "ok";
    }
    
}