package com.jumjari.zobiac.controller.member;

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

        Cookie accessCookie = new Cookie("access_token", result.access());
        accessCookie.setHttpOnly(true);
        accessCookie.setPath("/");
        accessCookie.setMaxAge(60 * 30);

        Cookie refreshCookie = new Cookie("refresh_token", result.refresh());
        refreshCookie.setHttpOnly(true);
        // cookie.setSecure(true);  //배포할때
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(60 * 60 * 24 * 7);

        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);
        return "redirect:/";
    }
    
    @PostMapping("/refresh")
    @ResponseBody
    public ResponseEntity<String> refreshToken(@CookieValue("refresh_token") String refresh) {        
        if (jwt.getTokenType(refresh) != TokenType.REFRESH) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        
        long id = jwt.getUserId(refresh);
        User user = userSearch.getUser(id);

        Member member = new Member(user);
        String access = jwt.createAccessToken(member);

        return ResponseEntity.ok(access);
    }    
}