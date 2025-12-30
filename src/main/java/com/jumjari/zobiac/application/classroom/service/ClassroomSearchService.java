package com.jumjari.zobiac.application.classroom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.classroom.dto.Classroom;
import com.jumjari.zobiac.application.classroom.mapper.ClassroomMapper;
import com.jumjari.zobiac.domain.classroom.ClassroomRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class ClassroomSearchService {
    private final ClassroomRepository repository;
    private final ClassroomMapper mapper;

    Optional<Classroom> getClassroomById(Long id) {
        return repository.findById(id)
            .map(mapper::toDto);
    }
    List<Classroom> getClassroomsByBuildingTrue(String building) {
        return repository.findAllByRoom_Building_korFullAndIsActiveTrue(building)
            .stream()
            .map(mapper::toDto)
            .toList();
    }
}