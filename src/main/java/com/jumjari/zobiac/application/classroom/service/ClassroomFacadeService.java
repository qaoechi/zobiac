package com.jumjari.zobiac.application.classroom.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.classroom.dto.ClassroomDetail;
import com.jumjari.zobiac.application.classroom.dto.ClassroomBoardResponse;
import com.jumjari.zobiac.application.classroom.dto.ClassroomRequest;
import com.jumjari.zobiac.domain.classroom.entity.BuildingEntity;
import com.jumjari.zobiac.domain.classroom.entity.Status;

@Service
@RequiredArgsConstructor
@Transactional
public class ClassroomFacadeService {
    private final ClassroomSearchService search;
    private final ClassroomCommandService commnad;

    // public ClassroomDetail getById(Long id) {
    //     return search.getClassroomById(id)
    //         .orElseThrow(() -> new EntityNotFoundException("classroom not found"));
    // }
    public List<ClassroomDetail> getClassroomsByBuilding(String korFull) {
        return search.getClassroomsByBuilding(korFull);
    }
    public Map<Status, List<ClassroomBoardResponse>> getGroups() {
        return search.getGroupsByStatus();
    }

    public void updateClassroom(ClassroomRequest requset) {
        commnad.updateClassroom(requset);
    }
    public void create(ClassroomRequest request, BuildingEntity building) {
        commnad.create(request, building);
    }
}