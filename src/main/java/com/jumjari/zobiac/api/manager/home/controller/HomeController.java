package com.jumjari.zobiac.api.manager.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller("managerHome")
@RequiredArgsConstructor
@RequestMapping("/manager")
public class HomeController {
    @GetMapping("")
    public String managerHome() {
        return "redirect:/manager/schedule";
    }
}   