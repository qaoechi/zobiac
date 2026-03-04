package com.jumjari.zobiac.application.schedule.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.schedule.dto.Availability;
import com.jumjari.zobiac.application.schedule.dto.Participant;
import com.jumjari.zobiac.application.schedule.mapper.AvailabilityMapper;
import com.jumjari.zobiac.application.schedule.mapper.ParticipantMapper;
import com.jumjari.zobiac.domain.schedule.repository.AvailabilityRepository;

@Service
@RequiredArgsConstructor
@Transactional
class AvailabilitySearchService {
    private final AvailabilityRepository repository;
    private final AvailabilityMapper mapper;
    private final ParticipantMapper partMapper;

    void saveAll(List<Availability> availabilities) {
        repository.saveAll(availabilities.stream().map(mapper::toEntity).toList());
    }

    void deleteByParticipant(Participant participant) {
        repository.deleteAllByParticipant(partMapper.toEntity(participant));
    }
}