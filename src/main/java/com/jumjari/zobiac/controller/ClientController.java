package com.jumjari.zobiac.controller;


import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jumjari.zobiac.application.building.BuildingFacadeService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/jumjari/client")
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