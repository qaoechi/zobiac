package com.jumjari.zobiac.application.classroom.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.classroom.dto.ClassroomRequest;
import com.jumjari.zobiac.domain.classroom.entity.BuildingEntity;
import com.jumjari.zobiac.domain.classroom.entity.ClassroomEntity;
import com.jumjari.zobiac.domain.classroom.entity.RoomEntity;
import com.jumjari.zobiac.domain.classroom.repository.ClassroomRepository;

@Service
@RequiredArgsConstructor
@Transactional
class ClassroomCommandService {
    private final ClassroomRepository repository;
    private final BuildingService buildingService;
    private final RoomService roomService;

    void updateClassroom(ClassroomRequest requset) {
        if (requset.getId() == requset.getParentId() || requset.getBuilding() == null) new IllegalArgumentException("not select");
        BuildingEntity building = buildingService.getById(requset.getBuilding());
        if (requset.getId() == null) {
            create(requset, building);
        } else {
            update(requset, building);
        }
    }

    void create(ClassroomRequest request, BuildingEntity building) {
        RoomEntity room = roomService.findOrCreate(request, building);
        ClassroomEntity classroom = ClassroomEntity.create(
            room, request.getName(),
            request.getDirection(),
            request.getDoorType(),
            Byte.valueOf(request.getCount()),
            request.getStatus(),
            (request.getParentId() == null) ? null : repository.findById(request.getParentId()).orElseThrow(() -> new IllegalArgumentException("not found")),
            request.getMemo()
        );
        repository.save(classroom);
    }
    private void update(ClassroomRequest request, BuildingEntity building) {
        ClassroomEntity classroom = repository.findById(request.getId())
            .orElseThrow(() -> new IllegalArgumentException("not found"));
        RoomEntity room = classroom.getRoom();

        RoomEntity targetRoom;
        if (repository.countByRoomId(room.getId()) > 1) {
            targetRoom = roomService.findOrCreate(request, building);
        } else {
            room.update(building, request.getNumber(), request.getFloor());
            targetRoom = room;
        }

        classroom.update(
            targetRoom,
            request.getDirection(),
            request.getDoorType(),
            Byte.valueOf(request.getCount()),
            request.getStatus(),
            (request.getParentId() == null) ? null : repository.findById(request.getParentId()).orElseThrow(),
            request.getMemo());
    }
}