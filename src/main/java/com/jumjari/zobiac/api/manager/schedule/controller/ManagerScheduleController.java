package com.jumjari.zobiac.controller.manger.schedule;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerScheduleController {
    @GetMapping("/schedule")
    public String managerPage(Model model) {
        model.addAllAttributes(Map.of(
            "main", "schedule",
            "sub", "써브"
        ));
        return "manager";
    }
}