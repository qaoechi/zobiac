package com.jumjari.zobiac.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.member.service.RefreshTokenService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final RefreshTokenService service;

    @GetMapping("/login")
    public String loginPage() {
        return "member";
    }

    @PostMapping("/logout")
    public String logout(
        @CookieValue(value = "access_token", required = false) String access,
        @CookieValue(value = "refresh_token", required = false) String refresh,
        HttpServletResponse response
    ) {
        if (refresh != null) service.deleteAllByToken(refresh);

        Cookie accessCookie = new Cookie("access_token", null);
        accessCookie.setMaxAge(0);
        accessCookie.setPath("/");
        response.addCookie(accessCookie);

        Cookie refreshCookie = new Cookie("refresh_token", null);
        refreshCookie.setMaxAge(0);
        refreshCookie.setPath("/");
        response.addCookie(refreshCookie);

        return "redirect:/home";
    }
}