package com.jumjari.zobiac.application.schedule.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.member.service.UserSearchService;
import com.jumjari.zobiac.domain.member.User;
import com.jumjari.zobiac.domain.schedule.entity.ParticipantEntity;
import com.jumjari.zobiac.domain.schedule.repository.ParticipantRepository;

@Service
@RequiredArgsConstructor
@Transactional
class ParticipantService {
    private final ParticipantRepository repository;
    private final MeetingService meetingService;
    private final UserSearchService userService;

    ParticipantEntity getGuestOrCreate(Long meetingId, String token) {
        return repository.findByMeetingIdAndToken(meetingId, token)
            .orElseGet(() -> {
                ParticipantEntity p = new ParticipantEntity();
                p.setMeeting(meetingService.getEntityById(meetingId));
                p.setToken(token);
                // p.setName();
                return repository.save(p);
            });
    }
    ParticipantEntity getUserOrCreate(Long meetingId, Long userId) {
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
}