package com.jumjari.zobiac.application.classroom.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.classroom.dto.Classroom;
import com.jumjari.zobiac.application.classroom.dto.Sign;
import com.jumjari.zobiac.application.room.dto.Room;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class SignService {
    private final ClassroomSearchService service;

    List<Sign> getSigns(String building) {
        List<Sign> result = new ArrayList<>();

        List<Classroom> classrooms = service.getClassroomsByBuildingTrue(building);
        for (Classroom classroom : classrooms) {
            Room room = classroom.getRoom();
            String floor = (room.getFloor() < 0) ? "B" + room.getNumber() : room.getNumber();
            String placard;
            
            if(floor.length() <= 2) {
                placard = classroom.getName();
            } else {
                placard = room.getBuilding().getKorShort() + " " + floor;
                if (!classroom.getName().isEmpty()) {
                    placard += " " + classroom.getName();
                }
            }
            String front = null, back = null, other = null, memo = null;
            switch (classroom.getCount()) {
                case 1 -> {
                    front = placard;
                    break;
                }
                case 3 -> {
                    other = placard + " 중";
                }
                case 2 -> {
                    front = placard + " 앞";
                    back = placard + " 뒤";
                }
            }
            if(classroom.getDirection().getDirection().equals("LEFT")) {
                placard = "←" + placard;
            } else if (classroom.getDirection().getDirection().equals("RIGHT")) {
                placard = placard + "→";
            }
            memo = classroom.getMemo();

            Sign sign = new Sign(classroom.getId(), floor, placard, front, back, other, memo);
            result.add(sign);
        }
        return result;
    }
}