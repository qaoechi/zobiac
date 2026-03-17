package com.jumjari.zobiac.controller.client.classroom;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.building.service.BuildingService;
import com.jumjari.zobiac.application.classroom.service.ClassroomFacadeService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClassroomController {
    private final BuildingService buildingService;
    private final ClassroomFacadeService classroomService;

    @GetMapping("/classroom/{building}")
    public String classroomDashboard(
        @PathVariable("building") String building,
        Model model
    ) {
        model.addAllAttributes(Map.of(
            "main", "dashboard",
            "building_name", buildingService.getKorFullByEngShort(building),
            "url", building,
            "signs", classroomService.getSigns(buildingService.getKorShortByEngShort(building))
        ));
        return "client";
    }

    @GetMapping("/classroom/{building}/editor")
    public String editor(
        @PathVariable("building") String building,
        Model model
    ) {
        model.addAllAttributes(Map.of(
            "main", "editor",
            "building_name", buildingService.getKorFullByEngShort(building),
            "url", building
        ));
        return "client";
    }
}