package com.jumjari.zobiac.application.schedule.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.schedule.dto.Meeting;
import com.jumjari.zobiac.application.schedule.dto.MeetingRequest;
import com.jumjari.zobiac.application.schedule.dto.MeetingResponse;
import com.jumjari.zobiac.application.schedule.mapper.MeetingMapper;
import com.jumjari.zobiac.domain.schedule.entity.MeetingEntity;
import com.jumjari.zobiac.domain.schedule.repository.MeetingRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MeetingService {
    private final MeetingRepository repository;
    private final MeetingMapper mapper;

    public MeetingResponse save(MeetingRequest request) {
        MeetingEntity m = mapper.toEntity(request);
        repository.save(m);
        return mapper.toResponse(m);
    }

    public Meeting getById(Long id) {
        return repository.findById(id)
            .map(mapper::toDto)
            .orElseThrow(() -> new IllegalArgumentException("meeting not found"));
    }
    public MeetingEntity getEntityById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("meeting not found"));
    }
    public List<MeetingResponse> getAllPublic() {
        return repository.findAllByOpen(true)
            .stream()
            .map(mapper::toResponse)
            .toList();
    }
    public List<Meeting> getAllPublicDetails() {
        return repository.findAllByOpen(true)
            .stream()
            .map(mapper::toDto)
            .toList();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}