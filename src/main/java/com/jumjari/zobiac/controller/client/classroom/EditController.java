package com.jumjari.zobiac.controller.client.classroom;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.building.service.BuildingService;
import com.jumjari.zobiac.application.classroom.dto.ClassroomRequest;
import com.jumjari.zobiac.application.classroom.service.ClassroomFacadeService;
import com.jumjari.zobiac.application.classroom.service.ClassroomService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client/edit")
public class EditController {
    private final BuildingService buildingService;
    private final ClassroomFacadeService classroomService;
    private final ClassroomService service;

    @GetMapping("/{building}")
    public String editPage(
        @PathVariable("building") String building,
        Model model
    ) {
        ClassroomRequest classroom = new ClassroomRequest();
        classroom.setDoorType("IDK");
        classroom.setBuilding(buildingService.getByEng(building).getId());
        
        model.addAllAttributes(Map.of(
            "main", "editor",
            "url", building,
            "building_name", buildingService.getKorFullByEngShort(building),
            "buildings", buildingService.getAll(),
            "classrooms", classroomService.getClassroomsByBuildingTrue(buildingService.getKorFullByEngShort(building)),
            "request", classroom
        ));
        return "client";
    }

    @PostMapping("/classroom/save")
    public String postMethodName(
        @ModelAttribute("request") ClassroomRequest request,
        @RequestParam(name = "basement", required = false) boolean basement
    ) {
        byte number = Byte.parseByte(request.getNumber().substring(0, 1));
        byte floor = (byte) (basement ? -number : number);
        request.setFloor(floor);
        System.out.println("\n\n\n\n\n문 타입: " + request.getDoorType());
        service.updateClassroom(request);
        return "redirect:/home";
    }
}