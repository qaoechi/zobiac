package com.jumjari.zobiac.application.classroom.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

import com.jumjari.zobiac.domain.classroom.ClassroomRepository;
import com.jumjari.zobiac.application.mapper.ClassroomMapper;
import com.jumjari.zobiac.application.classroom.dto.Classroom;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ClassroomSearchService {
    private final ClassroomRepository repository;
    private final ClassroomMapper mapper;

    public Classroom getClassroomById(Long id) {
        return repository.findById(id)
            .map(mapper::toDto)
            .orElse(null);
    }
}