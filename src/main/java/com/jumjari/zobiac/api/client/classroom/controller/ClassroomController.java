package com.jumjari.zobiac.api.client.classroom.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.classroom.dto.ClassroomBoardResponse;
import com.jumjari.zobiac.application.classroom.service.BuildingService;
import com.jumjari.zobiac.application.classroom.service.ClassroomFacadeService;
import com.jumjari.zobiac.application.classroom.service.ClassroomSignService;
import com.jumjari.zobiac.application.classroom.service.SignHelperService;
import com.jumjari.zobiac.domain.classroom.entity.Status;


@Controller("clientClassroom")
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClassroomController {
    private final BuildingService buildingService;
    private final ClassroomFacadeService classroomService;
    private final ClassroomSignService signService;
    private final SignHelperService hepler;

    @GetMapping("/classroom/{building}")
    public String classroomDashboard(
        @PathVariable("building") String building,
        Model model
    ) {
        Map<Status, List<ClassroomBoardResponse>> group = classroomService.getGroups();
        model.addAllAttributes(Map.of(
            "building_name", buildingService.getKorFullByEngShort(building),
            "url", building,
            "unchecked", group.getOrDefault(Status.UNCHECKED, List.of()),
            "stale", group.getOrDefault(Status.STALE, List.of()),
            "normal", group.getOrDefault(Status.NORMAL, List.of()),
            "signs", signService.getSigns(classroomService.getClassroomsByBuilding(buildingService.getKorShortByEngShort(building)))
        ));
        return "page/classroom/dashboard";
    }

    @GetMapping("/{building}/download")
    public ResponseEntity<byte[]> downloadFile(
        @PathVariable("building") String building,
        @RequestParam String type
    ) {
        Status status = Status.valueOf(type.toUpperCase());
        return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=" + building + ".csv")
            .header("Content-type", "text/csv")
            .body(hepler.getBytes(signService.getCsv(signService.getSigns(classroomService.getClassroomsByBuildingAndStatus(buildingService.getKorShortByEngShort(building), status)))));
    }
    
}