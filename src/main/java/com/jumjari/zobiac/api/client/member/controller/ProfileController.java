package com.jumjari.zobiac.api.client.member.controller;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jumjari.zobiac.application.member.Member;
import com.jumjari.zobiac.application.member.ProfileRequest;
import com.jumjari.zobiac.application.member.service.UserSearchService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
            "main", "profile",
            "profile_request", new ProfileRequest(null, null, null)
        ));
        return "client";
    }

    @PostMapping("/profile/update")
    public String postMethodName(
        ProfileRequest request,
        Authentication auth
    ) {
        Member member = (Member)auth.getPrincipal();
        userService.updateProfile(member.getId(), request);

        return "redirect:/home";
    }
    
}