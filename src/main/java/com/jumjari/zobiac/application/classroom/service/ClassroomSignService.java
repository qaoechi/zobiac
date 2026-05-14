package com.jumjari.zobiac.application.classroom.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.classroom.dto.ClassroomDetail;
import com.jumjari.zobiac.application.classroom.dto.Room;
import com.jumjari.zobiac.application.classroom.dto.ClassroomSign;
import com.jumyeok.ClassroomJumyeok;

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
                placard = room.getBuilding().getKorShort() + floor;
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
    public List<String> getCsv(List<ClassroomSign> signs) {
        List<String> line = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (ClassroomSign sign : signs) {
            sb.setLength(0);
            sb.append(nvl(sign.getId())).append(",")
            .append(nvl(sign.getNumber())).append(",")
            .append(nvl(sign.getPlacard())).append(",")
            .append(nvl(sign.getFront())).append(",")
            .append(nvl(sign.getBack())).append(",")
            .append(nvl(sign.getOther())).append(",")
            .append(nvl(sign.getMemo())).append("\n");
            line.add(sb.toString());
        }
        return line;
    }
    public byte[] getBRF(List<ClassroomSign> file) {
        ClassroomJumyeok jumyeok = new ClassroomJumyeok();
        jumyeok.setInput(file.stream().map(ClassroomSign::toString).toList());
        return jumyeok.classroomSign().getBytes();
    }

    private String nvl(Object o) {
        return (o == null) ? "" : o.toString();
    }
}