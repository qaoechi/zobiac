package com.jumjari.zobiac.controller.client.schedule;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("client/schedule")
public class ScheduleController {
    @GetMapping("")
    public String schedule() {
        return "client";
    }
}