package com.jumjari.zobiac.application.classroom.service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.classroom.dto.ClassroomDetail;
import com.jumjari.zobiac.application.classroom.dto.Room;
import com.jumjari.zobiac.application.classroom.dto.ClassroomSign;

@Service
@RequiredArgsConstructor
public class ClassroomSignService {
    public List<ClassroomSign> getSigns(List<ClassroomDetail> classrooms) {
        List<ClassroomSign> result = new ArrayList<>();
        for (ClassroomDetail classroom : classrooms) {
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
            switch (classroom.getDirection()) {
                case LEFT -> placard = "←" + placard;
                case RIGHT -> placard = placard + "→";
                case NO_SIGN -> {}
            }
            memo = classroom.getMemo();

            ClassroomSign sign = new ClassroomSign(classroom.getId(), floor, placard, front, back, other, memo);
            result.add(sign);
        }
        return result;
    }

    public byte[] getCsv(List<ClassroomSign> signs) {
        StringBuilder sb = new StringBuilder();
        sb.append("ID, 호수, 표지판, 앞문, 뒷문, 중문{기타}\n");
        for (ClassroomSign sign : signs) {
            sb.append(sign.getId()).append(",")
            .append(sign.getNumber()).append(",")
            .append(sign.getPlacard()).append(",")
            .append(sign.getFront()).append(",")
            .append(sign.getBack()).append(",")
            .append(sign.getOther()).append(",")
            .append(sign.getMemo()).append("\n");
        }
        return sb.toString().replaceAll("null", "").getBytes(StandardCharsets.UTF_8);
    }
}