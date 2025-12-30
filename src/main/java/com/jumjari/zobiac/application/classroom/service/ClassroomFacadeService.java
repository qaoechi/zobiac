package com.jumjari.zobiac.application.classroom.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.classroom.dto.Classroom;

@Service
@RequiredArgsConstructor
@Transactional
public class ClassroomFacadeService {
    private final ClassroomSearchService search;

    public Classroom getById(Long id) {
        return search.getClassroomById(id);
    }
    public List<Classroom> getClassroomsByBuildingTrue(String building) {
        return search.getClassroomsByBuildingTrue(building);
    }
}