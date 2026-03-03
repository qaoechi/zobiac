package com.jumjari.zobiac.controller.client;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/client")
public class ScheduleController {
    @GetMapping("/schedule")
    public String schedule(Model model) {
        model.addAllAttributes(Map.of(
            "main", "schedule"
        ));
        return "client";
    }
    
}