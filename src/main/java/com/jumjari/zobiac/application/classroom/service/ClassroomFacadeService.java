package com.jumjari.zobiac.application.classroom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import com.jumjari.zobiac.application.classroom.dto.Classroom;
import com.jumjari.zobiac.application.classroom.dto.Sign;

@Service
@AllArgsConstructor
public class ClassroomFacadeService {
    private final ClassroomSearchService search;
    private final SignService sign;

    public Classroom getById(Long id) {
        return search.getClassroomById(id);
    }
    public List<Sign> getSigns(String building) {
        return sign.getSigns(building);
    }
}