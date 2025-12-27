package com.jumjari.zobiac.controller.client.classroom.history;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/client/classroom")
public class ClassroomHistoryController {

    @GetMapping("/{building}/history")
    public String classroomHistory(
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

    @GetMapping("/{building}/history/{roomId}")
    @ResponseBody
    public List<?> getLogs(@PathVariable("roomId") Long id) {
        return List.of();
    }
}