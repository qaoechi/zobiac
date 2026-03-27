package com.jumjari.zobiac.application.classroom.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.classroom.dto.ClassroomBoardResponse;
import com.jumjari.zobiac.application.classroom.dto.ClassroomDetail;
import com.jumjari.zobiac.application.classroom.mapper.ClassroomMapper;
import com.jumjari.zobiac.domain.classroom.entity.ClassroomEntity;
import com.jumjari.zobiac.domain.classroom.entity.Status;
import com.jumjari.zobiac.domain.classroom.repository.ClassroomRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class ClassroomSearchService {
    private final ClassroomRepository repository;
    private final ClassroomMapper mapper;

    Map<Status, List<ClassroomBoardResponse>> getGroupsByStatus() {
        List<ClassroomEntity> entities = repository.findAll();
        return mapper.toBoards(entities).stream()
            .collect(Collectors.groupingBy(ClassroomBoardResponse::getStatus));
    }
    List<ClassroomDetail> getClassroomsByBuilding(String korFull) {
        return mapper.toDetails(repository.findAllByRoom_Building_korFull(korFull));
    }
    List<ClassroomDetail> getClassroomsByBuildingAndStatus(String korfull, Status status) {
        return mapper.toDetails(repository.findAllByRoom_Building_korFullAndStatus(korfull, status));
    }
}