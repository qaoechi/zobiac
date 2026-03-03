package com.jumjari.zobiac.application.schedule.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jumjari.zobiac.application.schedule.dto.Meeting;
import com.jumjari.zobiac.application.schedule.dto.Participant;
import com.jumjari.zobiac.application.schedule.mapper.ParticipantMapper;
import com.jumjari.zobiac.domain.schedule.repository.ParticipantRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
class ParticipantSearchService {
    private final ParticipantRepository repository;
    private final ParticipantMapper mapper;

    Participant getByMeetingAndToken(Meeting meeting, String token) {
        return repository.findByMeetingAndToken(meeting, token)
            .map(mapper::toDto)
            .orElseThrow(() -> new IllegalArgumentException("no participant"));
    }
}