package com.jumjari.zobiac.application.schedule.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jumjari.zobiac.application.schedule.dto.Availability;
import com.jumjari.zobiac.application.schedule.dto.Participant;
import com.jumjari.zobiac.application.schedule.mapper.AvailabilityMapper;
import com.jumjari.zobiac.application.schedule.mapper.ParticipantMapper;
import com.jumjari.zobiac.domain.schedule.repository.AvailabilityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
class AvailabilitySearchService {
    private final AvailabilityRepository repository;
    private final AvailabilityMapper mapper;
    private final ParticipantMapper partMapper;

    Availability save(Availability avail) {
        return mapper.toDto(repository.save(mapper.toEntity(avail)));
    }

    void deleteByParticipant(Participant participant) {
        repository.deleteAllByParticipant(partMapper.toEntity(participant));
    }
}