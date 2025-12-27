package com.jumjari.zobiac.controller.client.classroom.editor;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/client/classroom")
public class ClassroomEditorController {

    @GetMapping("/{building}/editor")
    public String classroomEditor(
        @PathParam("building") String building,
        HttpServletResponse response,
        Model model
    ) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        model.addAllAttributes(Map.of(
            null, null
        ));
        return "client";
    }
    @PostMapping("/{building}/update")
    public String updateClassroom(
        @PathVariable("building") String building
    ) {

        return "redirect:/client/classroom/" + building + "/editor";
    }
    @PostMapping("/{building}/delete")
    public String inactiveClassroom(
        @PathVariable("building") String building
    ) {
                
        return "redirect:/client/classroom/" + building + "/editor";
    }
}