package com.jumjari.zobiac.api.manager.schedule.controller;

import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jumjari.zobiac.api.manager.schedule.dto.MeetingRequest;

import org.springframework.web.bind.annotation.GetMapping;

@Controller("ManagerSchedule")
@PreAuthorize("hasRole('MANAGER')")
@RequestMapping("/manager")
public class ScheduleController {
    @GetMapping("/schedule")
    public String managerPage(Model model) {
        model.addAllAttributes(Map.of(
            "request", new MeetingRequest()
        ));
        return "manager/schedule/manage";
    }
}