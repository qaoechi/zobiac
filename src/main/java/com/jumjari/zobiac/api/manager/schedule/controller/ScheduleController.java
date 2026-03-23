package com.jumjari.zobiac.api.manager.schedule.controller;

import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jumjari.zobiac.api.manager.schedule.dto.MeetingRequest;
import com.jumjari.zobiac.application.schedule.service.MeetingService;
import com.jumjari.zobiac.global.security.principal.Member;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller("ManagerSchedule")
@RequiredArgsConstructor
@PreAuthorize("hasRole('MANAGER')")
@RequestMapping("/manager")
public class ScheduleController {
    private final MeetingService meetingService;

    @GetMapping("/schedule")
    public String managerPage(Model model) {
            model.addAllAttributes(Map.of(
            "request", new MeetingRequest(),
            "meetings", meetingService.getAll()
        ));
        return "manager/schedule/manage";
    }
    @PostMapping("/schedule/update")
    public String postMethodName(
        @ModelAttribute MeetingRequest request,
        Authentication auth
    ) {
        meetingService.updateMeeting(request, ((Member)auth.getPrincipal()).getId());
        return "redirect:/manager/schedule";
    }
    
}