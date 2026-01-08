package com.jumjari.zobiac.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/oauth")
public class OAuthController {
    @Value("${kakao.client-id}")
    private String clientId;
    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    @GetMapping("/kakao")
    public String redirectToKakao() {
        String url = "https://kauth.kakao.com/oauth/authorize" +
            "?client_id=" + clientId +
            "&redirect_uri=" + redirectUri + 
            "&response_type=code";

        return "redirect:" + url;
    }
    
}