package com.jumjari.zobiac.application.room.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jumjari.zobiac.domain.room.RoomRepository;
import com.jumjari.zobiac.application.mapper.RoomMapper;
import com.jumjari.zobiac.application.room.dto.Room;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class RoomSearchService {
    private final RoomRepository repository;
    private final RoomMapper mapper;

    public List<Room> getAllByBuilding(String building) {
        return repository.findAllByBuilding(building)
            .stream()
            .map(mapper::toRoomDto)
            .toList();
    }
    public boolean roomExists(Room room) {
        return repository.existsByBuildingAndNumberAndFloor(
            room.getBuilding().getKorFull(), room.getNumber(), room.getFloor()
        );
    }
    public Room getRoomByContents(Room room) {
        return repository.findByBuildingAndNumberAndFloor(
            room.getBuilding().getKorFull(), room.getNumber(), room.getFloor())
            .map(mapper::toRoomDto)
            .orElse(null);
    }
}