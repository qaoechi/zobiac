package com.jumjari.zobiac.controller.client.classroom;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jumjari.zobiac.application.classroom.service.ClassroomFacadeService;
import com.jumjari.zobiac.application.room.service.RoomFacadeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClassroomController {
    private final ClassroomFacadeService classroomService;
    private final RoomFacadeService roomService;

    @GetMapping("/classroom")
    public String chooseBuilding(Model model) {
        model.addAllAttributes(Map.of(
            "classrooms", classroomService.getClassroomsByBuildingTrue("정문"),
            "rooms", roomService.getRoomsByBuilding("정문")
        ));
        return "classroom-page";
    }
}