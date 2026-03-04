package com.jumjari.zobiac.application.schedule.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.member.service.UserSearchService;
import com.jumjari.zobiac.application.schedule.dto.Meeting;
import com.jumjari.zobiac.application.schedule.dto.Participant;
import com.jumjari.zobiac.application.schedule.mapper.ParticipantMapper;
import com.jumjari.zobiac.domain.schedule.repository.ParticipantRepository;

@Service
@RequiredArgsConstructor
@Transactional
class ParticipantSearchService {
    private final ParticipantRepository repository;
    private final MeetingService meeting;
    private final ParticipantMapper mapper;
    private final UserSearchService user;

    Participant getGuestOrCreate(Long meetingId, String token) {
        return repository.findByMeetingIdAndToken(meetingId, token)
            .map(mapper::toDto)
            .orElseGet(() -> {
                Participant p = new Participant();
                p.setMeeting(meeting.getById(meetingId));
                p.setToken(token);
                return mapper.toDto(repository.save(mapper.toEntity(p)));
            });
    }
    Participant getUserOrCreate(Long meetingId, Long userId) {
        return repository.findByMeetingIdAndUserId(meetingId, userId)
            .map(mapper::toDto)
            .orElseGet(() -> {
                Meeting m = meeting.getById(meetingId);
                Participant p = new Participant();
                p.setMeeting(m);
                p.setUser(user.getUser(userId));
                p.setName(user.getUser(userId).getNickname());
                return mapper.toDto(repository.save(mapper.toEntity(p)));
            });
    };
}