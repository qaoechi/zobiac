package com.jumjari.zobiac.application.schedule.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jumjari.zobiac.application.schedule.dto.Availability;
import com.jumjari.zobiac.application.schedule.dto.Meeting;
import com.jumjari.zobiac.application.schedule.dto.Participant;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {
    private final ParticipantSearchService partSearch;
    private final AvailabilitySearchService availSearch;

    public void save(Meeting meeting, String token, List<Availability> dtos) {
        Participant part = partSearch.getByMeetingAndToken(meeting, token);
        availSearch.deleteByParticipant(part);

        for (Availability dto : dtos) {
            Availability avail = new Availability(null, part, dto.getWeek(), dto.getSlot());

            availSearch.save(avail);
        }
    }
}