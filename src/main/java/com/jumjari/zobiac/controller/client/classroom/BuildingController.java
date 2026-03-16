package com.jumjari.zobiac.controller.client.classroom;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.building.dto.Building;
import com.jumjari.zobiac.application.building.service.BuildingFacadeService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client")
public class BuildingController {
    private final BuildingFacadeService service;

    @GetMapping("/building")
    public String chooseBuilding(Model model) {
        model.addAllAttributes(Map.of(
            "main", "buildings",
            "buildings", service.getBuildings()
        ));
        return "client";
    }

    @GetMapping("/buildings")
    @ResponseBody
    public List<Building> getBuildings() {
        return service.getBuildings();
    }

    @PostMapping("move-building")
    public String moveTo(
        @RequestParam("option") String option,
        @RequestParam("name") String name
    ) {
        if (!option.isBlank()) {
            return "redirect:/client/classroom/" + option;
        } else {
            if (name == null || name.isBlank()) return "redirect:/client/building";
            return service.getByInput(name)
                .map(n -> "redirect:/client/classroom/" + n)
                .orElse("redirect:/client/building");
        }
    }
}