package com.jumjari.zobiac.controller;

import java.io.IOException;

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

import com.jumjari.zobiac.application.member.Member;
import com.jumjari.zobiac.application.member.service.KakaoOauthService;
import com.jumjari.zobiac.application.member.service.UserSearchService;
import com.jumjari.zobiac.domain.member.User;
import com.jumjari.zobiac.infrastructure.security.JwtProvider;
import com.jumjari.zobiac.infrastructure.security.LoginResult;
import com.jumjari.zobiac.infrastructure.security.TokenType;

@Controller
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {
    private final KakaoOauthService service;
    private final UserSearchService userSearch;
    private final JwtProvider jwt;

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

        Cookie cookie = new Cookie("refresh_token", result.refresh());
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/auth/refresh");
        cookie.setMaxAge(60 * 60 * 24 * 7);

        response.addCookie(cookie);
        // response.sendRedirect("/");
        return "redirect:/";
    }
    
    @PostMapping("/refresh")
    @ResponseBody
    public ResponseEntity<String> refreshToken(@CookieValue("refresh_token") String refresh) {        
        if (jwt.getTokenType(refresh) != TokenType.REFRESH) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        
        long id = jwt.getUserId(refresh);
        User user = userSearch.getUser(id);

        // if (!refresh.equals(user.getRefreshToken())) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        Member member = new Member(user);
        String access = jwt.createAccessToken(member);

        return ResponseEntity.ok(access);
    }    
}