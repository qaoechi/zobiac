package com.jumjari.zobiac.controller.client.classroom;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jumjari.zobiac.application.building.dto.Building;
import com.jumjari.zobiac.application.building.service.BuildingFacadeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClassroomController {
    private final BuildingFacadeService buildingService;

    @GetMapping("/building")
    public String chooseBuilding(Model model) {
        return "building-page";
    }

    @GetMapping("/buildings")
    @ResponseBody
    public List<Building> getBuildings() {
        return buildingService.getBuildings();
    }
}