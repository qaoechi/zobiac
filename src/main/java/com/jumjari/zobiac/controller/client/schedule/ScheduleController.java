package com.jumjari.zobiac.controller.client.schedule;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.schedule.dto.AvailabilityRequest;
import com.jumjari.zobiac.application.schedule.service.MeetingService;
import com.jumjari.zobiac.application.schedule.service.ScheduleService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client")
public class ScheduleController {
    private final MeetingService meetingService;
    private final ScheduleService schedule;

    @GetMapping("/schedule")
    public String schedule(Model model) {
        model.addAllAttributes(Map.of(
            "main", "schedule",
            "sub", "dashboard",
            "meetings", meetingService.getAllPublic()
        ));
        return "client";
    }
    @GetMapping("/schedule/{meeting-id}")
    public String selectSchedule(
        Model model,
        @PathVariable("meeting-id") String meetingId
    ) {
        model.addAllAttributes(Map.of(
            "main", "schedule",
            "sub", "setup",
            "meetingId", meetingId
        ));
        return "client";
    }
    
    @PostMapping("/schedule/{meeting-id}/update")
    public String saveSchedule(
        @PathVariable("meeting-id") Long meetingId,
        @CookieValue(value = "guestToken", required = false) String token,
        @RequestBody List<AvailabilityRequest> dtos,
        Authentication auth
    ) {
        schedule.save(meetingId, auth, token, dtos);
        return "redirect:/client/schedule/" + meetingId;
    }
}