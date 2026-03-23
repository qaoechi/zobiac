package com.jumjari.zobiac.api.client.member.controller;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.member.dto.ProfileRequest;
import com.jumjari.zobiac.application.member.service.UserSearchService;
import com.jumjari.zobiac.global.security.principal.Member;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client")
public class ProfileController {
    private final UserSearchService userService;

    @GetMapping("/profile")
    public String profile(
        Model model
    ) {
        model.addAllAttributes(Map.of(
            "profile_request", new ProfileRequest()
        ));
        return "page/profile/setting";
    }

    @PostMapping("/profile/update")
    public String postMethodName(
        @ModelAttribute("profile_request") ProfileRequest request,
        Authentication auth
    ) {
        Member member = (Member)auth.getPrincipal();
        System.out.println("username = " + request.getUsername());
        System.out.println("number = " + request.getNumber());
        System.out.println("nickname = " + request.getNickname());
        userService.updateProfile(member.getId(), request);
        return "redirect:/home";
    }
}