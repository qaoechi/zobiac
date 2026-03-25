package com.jumjari.zobiac.api.client.classroom.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.classroom.service.BuildingService;
import com.jumjari.zobiac.application.classroom.service.ClassroomFacadeService;

@Controller("clientClassroom")
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
            "building_name", buildingService.getKorFullByEngShort(building),
            "url", building,
            // "modify", classroomService.get()
            "signs", classroomService.getSigns(buildingService.getKorShortByEngShort(building))
        ));
        return "page/classroom/dashboard";
    }
}