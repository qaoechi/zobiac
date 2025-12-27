package com.jumjari.zobiac.application.room;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jumjari.zobiac.application.room.service.RoomSearchService;
import com.jumjari.zobiac.application.room.service.LifeService;
import com.jumjari.zobiac.application.room.dto.Room;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoomFacadeService {
    private final RoomSearchService search;
    private final LifeService life;

    public List<Room> getRoomsByBuilding(String building) {
        return search.getAllByBuilding(building);
    }
    public boolean exists(Room room) {
        return search.roomExists(room);
    }
    public Room getRoomByContents(Room room) {
        return search.getRoomByContents(room);
    }
    public Room create(Room room) {
        return life.create(room);
    }
    public void kill(Room room) {
        life.kill(room);
    }
}