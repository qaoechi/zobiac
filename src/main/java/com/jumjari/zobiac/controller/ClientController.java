package com.jumjari.zobiac.controller;


import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/jumjari/client")
public class ClientController {
    @GetMapping("")
    public String test(Model model) {
        model.addAllAttributes(Map.of("name", "qaoechi"));
        return "client";
    }
}