package com.jumjari.zobiac.controller;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;

import com.jumjari.zobiac.application.building.BuildingFacadeService;
import com.jumjari.zobiac.application.building.dto.Building;

@Controller
@AllArgsConstructor
@RequestMapping("/client")
public class ClientController {
    private final BuildingFacadeService buildingService;

    @GetMapping("/building")
    public String chooseBuilding(Model model) {
        model.addAllAttributes(Map.of(
            "main", "buildings"
        ));
        return "client";
    }
    @ResponseBody
    @GetMapping("/buildings")
    public List<Building> getMethodName() {
        return buildingService.getBuildings();
    }
}