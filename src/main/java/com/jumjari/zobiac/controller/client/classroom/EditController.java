package com.jumjari.zobiac.controller.client.classroom;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jumjari.zobiac.application.building.service.BuildingService;
import com.jumjari.zobiac.application.classroom.service.ClassroomFacadeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client/edit")
public class EditController {
    private final BuildingService buildingService;
    private final ClassroomFacadeService classroomService;

    @GetMapping("/{building}")
    public String editPage(
        @PathVariable("building") String building,
        Model model
    ) {
        model.addAllAttributes(Map.of(
            "main", "editor",
            "url", building,
            "building_name", buildingService.getKorFullByEngShort(building),
            "classrooms", classroomService.getClassroomsByBuildingTrue(buildingService.getKorFullByEngShort(building))
        ));
        return "client";
    }
}