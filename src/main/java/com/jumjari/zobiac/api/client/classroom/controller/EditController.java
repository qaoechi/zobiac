package com.jumjari.zobiac.api.client.classroom.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.classroom.dto.ClassroomRequest;
import com.jumjari.zobiac.application.classroom.service.BuildingService;
import com.jumjari.zobiac.application.classroom.service.ClassroomFacadeService;
import com.jumjari.zobiac.domain.classroom.entity.Status;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client/edit")
public class EditController {
    private final BuildingService buildingService;
    private final ClassroomFacadeService classroomService;

    @GetMapping("/{building}")
    public String editPage(
        @PathVariable("building") String building,
        HttpServletRequest request,
        Model model
    ) {
        model.addAllAttributes(Map.of(
            "url", building,
            "redirectUrl", request.getRequestURI(),
            "building_name", buildingService.getKorFullByEngShort(building),
            "buildings", buildingService.getAll(),
            "classrooms", classroomService.getClassroomsByBuilding(buildingService.getKorFullByEngShort(building)),
            "request", ClassroomRequest.formObject(buildingService.getByEng(building).getId())
        ));
        return "page/classroom/edit";
    }

    @PostMapping("/classroom/save")
    public String postMethodName(
        @Valid @ModelAttribute("request") ClassroomRequest request,
        BindingResult result,
        @RequestParam(name = "basement", required = false) boolean basement,
        @RequestParam(name = "redirectUrl") String url,
        Model model
    ) {
        if (result.hasErrors()) {
            String engshort = buildingService.getById(request.getBuilding()).getEngShort();
            model.addAllAttributes(Map.of(
                "url", "asdafign",
                "building_name", buildingService.getKorFullByEngShort(engshort),
                "buildings", buildingService.getAll(),
                "classrooms", classroomService.getClassroomsByBuilding(buildingService.getKorFullByEngShort(engshort)),
                "request", request
        ));
            return "page/classroom/edit";
        }
        
        byte number = Byte.parseByte(request.getNumber().substring(0, 1));
        byte floor = (byte) (basement ? -number : number);
        request.setFloor(floor);
        request.setStatus(Status.UNCHECKED);
        classroomService.updateClassroom(request);

        if (url == null || !url.startsWith("/")) url = "/";
        return "redirect:" + url;
    }
}