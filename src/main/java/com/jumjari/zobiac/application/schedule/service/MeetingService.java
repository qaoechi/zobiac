package com.jumjari.zobiac.application.schedule.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.schedule.dto.Meeting;
import com.jumjari.zobiac.application.schedule.mapper.MeetingMapper;
import com.jumjari.zobiac.domain.schedule.repository.MeetingRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MeetingService {
    private final MeetingRepository repository;
    private final MeetingMapper mapper;

    public Meeting getById(Long id) {
        return repository.findById(id)
            .map(mapper::toDto)
            .orElseThrow(() -> new IllegalArgumentException("meeting not found"));
    }
    public List<Meeting> getAllPublic() {
        return repository.findAllByOpen(true)
            .stream()
            .map(mapper::toDto)
            .toList();
    }
}