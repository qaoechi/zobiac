package com.jumjari.zobiac.application.room.service;

import org.springframework.stereotype.Service;

import com.jumjari.zobiac.domain.room.RoomRepository;
import com.jumjari.zobiac.application.mapper.RoomMapper;
import com.jumjari.zobiac.application.room.dto.Room;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LifeService {
    public final RoomRepository repository;
    public final RoomMapper mapper;

    public Room create(Room room) {
        return mapper.toRoomDto(
            repository.save(mapper.toRoomEntity(room))
        );
    }
    public void kill(Room room) {
        repository.delete(mapper.toRoomEntity(room));
    }
}