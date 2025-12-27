package com.jumjari.zobiac.application.classroom;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import com.jumjari.zobiac.application.classroom.service.ClassroomSearchService;
import com.jumjari.zobiac.application.classroom.dto.Classroom;

@Service
@AllArgsConstructor
public class ClassroomFacadeService {
    private final ClassroomSearchService search;

    public Classroom getById(Long id) {
        return search.getClassroomById(id);
    }
}