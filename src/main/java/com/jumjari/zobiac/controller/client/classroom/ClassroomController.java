package com.jumjari.zobiac.controller.client.classroom;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jumjari.zobiac.application.building.service.BuildingFacadeService;
import com.jumjari.zobiac.application.classroom.service.ClassroomFacadeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClassroomController {
    private final BuildingFacadeService buildingService;
    private final ClassroomFacadeService classroomService;

    @GetMapping("/classroom/{building}")
    public String classroomDashboard(
        @PathVariable("building") String building,
        Model model
    ) {
        model.addAllAttributes(Map.of(
            "building_name", buildingService.getKorFull(building),
            "url", building,
            "signs", classroomService.getSigns(buildingService.getKorFull(building))
        ));
        return "classroom-page";
    }
}