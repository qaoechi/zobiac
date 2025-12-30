package com.jumjari.zobiac.application.room.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.room.dto.Room;
import com.jumjari.zobiac.application.room.mapper.RoomMapper;
import com.jumjari.zobiac.domain.room.RoomRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class RoomSearchService {
    private final RoomRepository repository;
    private final RoomMapper mapper;

    List<Room> getAllByBuilding(String building) {
        return repository.findAllByBuilding_korFull(building)
            .stream()
            .map(mapper::toRoomDto)
            .toList();
    }
    boolean roomExists(Room room) {
        return repository.existsByBuilding_korFullAndNumberAndFloor(
            room.getBuilding().getKorFull(), room.getNumber(), room.getFloor()
        );
    }
    Optional<Room> getRoomByContents(Room room) {
        return repository.findByBuilding_korFullAndNumberAndFloor(
            room.getBuilding().getKorFull(), room.getNumber(), room.getFloor())
            .map(mapper::toRoomDto);
    }
}