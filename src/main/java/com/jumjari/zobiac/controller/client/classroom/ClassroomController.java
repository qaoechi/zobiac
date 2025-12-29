package com.jumjari.zobiac.controller.client.classroom;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jumjari.zobiac.application.building.service.BuildingFacadeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClassroomController {
    private final BuildingFacadeService buildingService;

    @GetMapping("/building")
    public String chooseBuilding(Model model) {
        model.addAllAttributes(Map.of(
            "buildings", buildingService.getBuildings()
        ));
        return "building-page";
    }
}