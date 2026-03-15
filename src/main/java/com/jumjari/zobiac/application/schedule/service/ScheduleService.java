package com.jumjari.zobiac.application.schedule.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.schedule.dto.AvailabilityRequest;
import com.jumjari.zobiac.domain.schedule.entity.ParticipantEntity;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {
    private final ParticipantService partService;
    private final AvailabilityService availService;

    public void save(Long meetingId, Authentication auth, String token, List<AvailabilityRequest> dtos) {
        ParticipantEntity part;
        if (auth != null && auth.isAuthenticated()) {
            part = partService.getUserOrCreate(meetingId, Long.parseLong(auth.getName()));
        } else {
            part = partService.getGuestOrCreate(meetingId, token);
        }
        availService.deleteByParticipant(part.getId());
        availService.flush();
        availService.saveAll(dtos, part);
    }
}