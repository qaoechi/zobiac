package com.jumjari.zobiac.application.classroom.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.classroom.dto.Classroom;
import com.jumjari.zobiac.application.classroom.dto.ClassroomSign;

import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class ClassroomFacadeService {
    private final ClassroomSearchService search;
    private final ClassroomSignService sign;

    public Classroom getById(Long id) {
        return search.getClassroomById(id)
            .orElseThrow(() -> new EntityNotFoundException("classroom not found"));
    }
    public List<Classroom> getClassroomsByBuildingTrue(String korFull) {
        return search.getClassroomsByBuildingTrue(korFull);
    }
    public List<ClassroomSign> getSigns(String korFull) {
        return sign.getSigns(korFull);
    }
}