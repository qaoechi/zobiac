package com.jumjari.zobiac.application.schedule.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.schedule.dto.Availability;
import com.jumjari.zobiac.application.schedule.dto.AvailabilityRequest;
import com.jumjari.zobiac.application.schedule.dto.Participant;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {
    private final ParticipantSearchService partSearch;
    private final AvailabilitySearchService availSearch;

    public void save(Long meetingId, Authentication auth, String token, List<AvailabilityRequest> dtos) {
        Participant part;
        if (auth != null && auth.isAuthenticated()) {
            part = partSearch.getUserOrCreate(meetingId, Long.parseLong(auth.getName()));
        } else {
            part = partSearch.getGuestOrCreate(meetingId, token);
        }
        availSearch.deleteByParticipant(part);

        List<Availability> availabilities = dtos.stream()
            .map(dto -> new Availability(
                null,
                part,
                dto.getWeek(),
                dto.getSlot()
            ))
            .toList();

        availSearch.saveAll(availabilities);
    }
}