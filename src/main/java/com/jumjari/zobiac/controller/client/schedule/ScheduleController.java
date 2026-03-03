package com.jumjari.zobiac.controller.client.schedule;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jumjari.zobiac.application.schedule.dto.Availability;

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
    @GetMapping("/schedule/{meeting-id}")
    public String selectSchedule(
        Model model,
        @PathVariable("meeting-id") String meetingId
    ) {
        model.addAllAttributes(Map.of(
            "main", "schedule",
            "sub", "",
            "meetingId", meetingId
        ));
        return "client";
    }
    
    @PostMapping("/schedule/{meeting-id}/update")
    public String saveSchedule(
        @PathVariable("meeting-id") String meetingId,
        @CookieValue("guestToken") String token,
        @RequestBody List<Availability> dtos
    ) {
        //update(meetingId, toekn, dtos);
        return "redirect:/client/schedule/" + meetingId;
    }
    
}