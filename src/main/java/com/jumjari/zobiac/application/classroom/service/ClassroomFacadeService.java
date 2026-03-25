package com.jumjari.zobiac.application.classroom.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.classroom.dto.Classroom;
import com.jumjari.zobiac.application.classroom.dto.ClassroomRequest;
import com.jumjari.zobiac.domain.classroom.entity.BuildingEntity;

@Service
@RequiredArgsConstructor
@Transactional
public class ClassroomFacadeService {
    private final ClassroomSearchService search;
    private final ClassroomCommandService commnad;

    public Classroom getById(Long id) {
        return search.getClassroomById(id)
            .orElseThrow(() -> new EntityNotFoundException("classroom not found"));
    }
    public List<Classroom> getClassroomsByBuildingTrue(String korFull) {
        return search.getClassroomsByBuildingTrue(korFull);
    }

    public void updateClassroom(ClassroomRequest requset) {
        commnad.updateClassroom(requset);
    }
    public void create(ClassroomRequest request, BuildingEntity building) {
        commnad.create(request, building);
    }
}