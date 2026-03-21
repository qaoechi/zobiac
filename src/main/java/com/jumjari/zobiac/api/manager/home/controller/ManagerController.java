package com.jumjari.zobiac.controller.manger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManageController {
    @GetMapping("")
    public String managerHome() {
        return "redirect:/manager/schedule";
    }
}