package com.jumjari.zobiac.application.classroom.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jumjari.zobiac.application.classroom.dto.Room;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomFacadeService {
    private final RoomSearchService search;
    private final LifeService life;

    public List<Room> getRoomsByBuilding(String korFull) {
        return search.getAllByBuilding(korFull);
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