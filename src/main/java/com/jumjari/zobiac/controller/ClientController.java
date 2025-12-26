package com.jumjari.zobiac.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import lombok.AllArgsConstructor;

import com.jumjari.zobiac.application.building.BuildingFacadeService;

@Controller
@AllArgsConstructor
@RequestMapping("/client")
public class ClientController {
    private final BuildingFacadeService buildingService;

    @GetMapping("")
    public String test(Model model) {
        model.addAllAttributes(Map.of(
            "name", "qaoechi",
            "buildings", buildingService.getBuildings(),
            "building", buildingService.getKorFull("sowe")
        ));
        return "client";
    }
}