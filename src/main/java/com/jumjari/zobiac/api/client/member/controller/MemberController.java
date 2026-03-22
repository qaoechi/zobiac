package com.jumjari.zobiac.api.client.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/login")
    public String loginPage() {
        return "layout/member";
    }

    @PostMapping("/logout")
    public String logout(
        HttpServletResponse response
    ) {
        return "redirect:/home";
    }
}