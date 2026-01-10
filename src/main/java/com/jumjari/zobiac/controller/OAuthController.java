package com.jumjari.zobiac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.member.service.KakaoOauthService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {
    private final KakaoOauthService service;

    @GetMapping("/kakao")
    public String redirectToKakao() {
        return service.getKakaoRedirectUrl();
    }
    @GetMapping("/kakao/callback")
    public String kakaoCallback(@RequestParam String code, HttpServletResponse response) {
        // return service.login(code);
        String jwt = service.login(code);

        response.addHeader("Authorization", "Bearer " + jwt);
        return "redirect:/";
    }
    
}