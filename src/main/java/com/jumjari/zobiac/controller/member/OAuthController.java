package com.jumjari.zobiac.controller.member;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.member.service.KakaoOauthService;
import com.jumjari.zobiac.application.member.service.RefreshTokenService;
import com.jumjari.zobiac.infrastructure.security.LoginResult;

@Controller
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {
    private final KakaoOauthService service;
    private final RefreshTokenService refreshService;

    @Value("${jwt.access}")
    private long access;
    @Value("${jwt.refresh}")
    private long refresh;

    @GetMapping("/kakao")
    public String redirectToKakao() {
        return service.getKakaoRedirectUrl();
    }
    @GetMapping("/kakao/callback")
    public String kakaoCallback(
            @RequestParam String code,
            HttpServletResponse response
        ) throws IOException {
        LoginResult result = service.login(code);

        Cookie accessCookie = new Cookie("access_token", result.access());
        accessCookie.setHttpOnly(true);
        accessCookie.setPath("/");
        accessCookie.setMaxAge((int)(access / 1000));

        Cookie refreshCookie = new Cookie("refresh_token", result.refresh().getToken());
        refreshCookie.setHttpOnly(true);
        // cookie.setSecure(true);  //배포할때
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge((int)(refresh / 1000));

        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);
        return "redirect:/";
    }
    
    @PostMapping("/refresh")
    @ResponseBody
    public ResponseEntity<?> refreshToken(
        @CookieValue(value = "refresh_token", required = false) String refresh,
        HttpServletResponse response
    ) {
        if (refresh == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        
        LoginResult result = refreshService.refresh(refresh);

        Cookie accessCookie = new Cookie("access_token", result.access());
        accessCookie.setHttpOnly(true);
        accessCookie.setPath("/");
        accessCookie.setMaxAge((int)(access / 1000));

        response.addCookie(accessCookie);

        return ResponseEntity.ok().build();
    }
}