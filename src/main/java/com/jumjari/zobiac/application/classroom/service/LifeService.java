package com.jumjari.zobiac.application.classroom.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.classroom.dto.Room;
import com.jumjari.zobiac.application.classroom.mapper.RoomMapper;
import com.jumjari.zobiac.domain.room.RoomRepository;

@Service
@RequiredArgsConstructor
@Transactional
class LifeService {
    private final RoomRepository repository;
    private final RoomMapper mapper;

    Room create(Room room) {
        return mapper.toRoomDto(
            repository.save(mapper.toRoomEntity(room))
        );
    }
    void kill(Room room) {
        repository.delete(mapper.toRoomEntity(room));
    }
}