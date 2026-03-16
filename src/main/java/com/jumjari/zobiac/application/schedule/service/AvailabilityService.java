package com.jumjari.zobiac.application.schedule.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.schedule.dto.AvailabilityRequest;
import com.jumjari.zobiac.application.schedule.dto.AvailabilityResponse;
import com.jumjari.zobiac.application.schedule.dto.ParticipantResponse;
import com.jumjari.zobiac.application.schedule.mapper.AvailabilityMapper;
import com.jumjari.zobiac.domain.schedule.entity.AvailabilityEntity;
import com.jumjari.zobiac.domain.schedule.entity.ParticipantEntity;
import com.jumjari.zobiac.domain.schedule.repository.AvailabilityRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class AvailabilityService {
    private final AvailabilityRepository repository;
    private final ParticipantService partService;
    private final AvailabilityMapper mapper;

    public List<AvailabilityResponse> saveAll(List<AvailabilityRequest> requests, ParticipantEntity part) {
        List<AvailabilityEntity> entities = repository.saveAll(mapper.toEntities(requests, part));
        return mapper.toResponses(entities);
    }

    public List<AvailabilityResponse> getAllByParticipant(Long id) {
        return mapper.toResponses(repository.findAllByParticipantId(id));
    }
    public List<AvailabilityResponse> getAllByAuthAndMeeting(Authentication auth, Long meetingId) {
        ParticipantResponse part = partService.getByUserIdAndMeetingId(auth, meetingId);
        return (part == null) ? List.of() : mapper.toResponses(repository.findAllByParticipantId(part.getId()));
    }

    public void deleteByParticipant(Long id) {
        repository.deleteAllByParticipantId(id);
        repository.flush();
    }
}