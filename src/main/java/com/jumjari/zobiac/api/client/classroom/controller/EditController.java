package com.jumjari.zobiac.api.client.classroom.controller;

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

import com.jumjari.zobiac.application.classroom.dto.ClassroomRequest;
import com.jumjari.zobiac.application.classroom.service.BuildingService;
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
        // ClassroomRequest classroom = new ClassroomRequest();
        // classroom.setDoorType("IDK");
        // classroom.setBuilding(buildingService.getByEng(building).getId());
        
        model.addAllAttributes(Map.of(
            "url", building,
            "building_name", buildingService.getKorFullByEngShort(building),
            "buildings", buildingService.getAll(),
            "classrooms", classroomService.getClassroomsByBuildingTrue(buildingService.getKorFullByEngShort(building)),
            "request", ClassroomRequest.formObject(buildingService.getByEng(building).getId())
        ));
        return "page/classroom/edit";
    }

    @PostMapping("/classroom/save")
    public String postMethodName(
        @ModelAttribute("request") ClassroomRequest request,
        @RequestParam(name = "basement", required = false) boolean basement
    ) {
        byte number = Byte.parseByte(request.getNumber().substring(0, 1));
        byte floor = (byte) (basement ? -number : number);
        request.setFloor(floor);
        service.updateClassroom(request);
        return "redirect:/home";
    }
}