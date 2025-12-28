package com.jumjari.zobiac.controller.client.classroom;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.building.BuildingFacadeService;
import com.jumjari.zobiac.application.building.dto.Building;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClassroomController {
    private final BuildingFacadeService buildingService;
    private String sub = "dashboard";

    @GetMapping("/building")
    public String chooseBuilding(Model model) {
        model.addAllAttributes(Map.of(
            "main", "buildings" 
        ));
        return "client";
    }
    @GetMapping("/buildings")
    @ResponseBody
    public List<Building> getBuildings() {
        return buildingService.getBuildings();
    }
    @PostMapping("move-building")
    public String moveTo() {
        return null;
    }
    @GetMapping("/classroom/{building}")
    public String classroomDashboard(
        @PathVariable("building") String building,
        Model model
    ) {
        model.addAllAttributes(Map.of(
            "main", "classroom",
            "sub", sub,
            "building", buildingService.getKorFull(building),
            "url", building
            ));
        return "client";
    }
}