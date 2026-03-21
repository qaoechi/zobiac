package com.jumjari.zobiac.application.classroom.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.classroom.dto.ClassroomRequest;
import com.jumjari.zobiac.domain.classroom.entity.BuildingEntity;
import com.jumjari.zobiac.domain.classroom.entity.RoomEntity;
import com.jumjari.zobiac.domain.classroom.repository.RoomRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {
    private final RoomRepository repository;

    public RoomEntity findOrCreate(ClassroomRequest request, BuildingEntity building) {
        return repository.findByBuilding_korFullAndNumberAndFloor(
            building.getKorFull(),
            request.getNumber(),
            request.getFloor())
        .orElseGet(() -> repository.save(
            new RoomEntity(null, building, request.getNumber(), request.getFloor())
        ));
    }
}