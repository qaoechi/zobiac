package com.jumjari.zobiac.application.schedule.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.member.service.UserSearchService;
import com.jumjari.zobiac.application.schedule.dto.ParticipantResponse;
import com.jumjari.zobiac.application.schedule.mapper.ParticipantMapper;
import com.jumjari.zobiac.domain.member.User;
import com.jumjari.zobiac.domain.schedule.entity.ParticipantEntity;
import com.jumjari.zobiac.domain.schedule.repository.ParticipantRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ParticipantService {
    private final ParticipantRepository repository;
    private final MeetingService meetingService;
    private final UserSearchService userService;
    private final ParticipantMapper mapper;

    public ParticipantEntity getGuestOrCreate(Long meetingId, String token) {
        return repository.findByMeetingIdAndToken(meetingId, token)
            .orElseGet(() -> {
                ParticipantEntity p = new ParticipantEntity();
                p.setMeeting(meetingService.getEntityById(meetingId));
                p.setToken(token);
                // p.setName();
                return repository.save(p);
            });
    }
    public ParticipantEntity getUserOrCreate(Long meetingId, Long userId) {
        return repository.findByMeetingIdAndUserId(meetingId, userId)
            .orElseGet(() -> {
                ParticipantEntity p = new ParticipantEntity();
                User user = userService.getUser(userId);
                p.setMeeting(meetingService.getEntityById(meetingId));
                p.setUser(user);
                p.setName(user.getNickname());
                return p;
            });
    };

    public ParticipantResponse getByUserIdAndMeetingId(Authentication auth, Long meetingId) {
        return mapper.toResponse(
            (auth != null && auth.isAuthenticated()) ?
            repository.findByMeetingIdAndUserId(meetingId, Long.parseLong(auth.getName()))
            .orElseThrow(() -> new IllegalArgumentException("participant not found")):
            repository.findByMeetingIdAndToken(meetingId, "asd")
            .orElseThrow(() -> new IllegalArgumentException("participant not found"))
        );
        // if (auth != null && auth.isAuthenticated()) {
        //     return mapper.toResponse(
        //         repository.findByMeetingIdAndUserId(meetingId, Long.parseLong(auth.getName()))
        //         .orElseThrow(() -> new IllegalArgumentException("participant not found"))
        //     );
        // } else {
        //     return mapper.toResponse(null)
        // }
        // return repository.
    }
}