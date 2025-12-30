package com.jumjari.zobiac.application.room.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.room.dto.Room;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomFacadeService {
    private final RoomSearchService search;
    private final LifeService life;

    public List<Room> getRoomsByBuilding(String building) {
        return search.getAllByBuilding(building);
    }
    public boolean exists(Room room) {
        return search.roomExists(room);
    }
    public Room getByContents(Room room) {
        return search.getRoomByContents(room)
            .orElseThrow(() -> new IllegalArgumentException("Room not found"));
    }
    public Room create(Room room) {
        return life.create(room);
    }
    public void kill(Room room) {
        life.kill(room);
    }
}