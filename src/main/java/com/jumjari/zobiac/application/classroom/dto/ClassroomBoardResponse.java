package com.jumjari.zobiac.application.classroom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.jumjari.zobiac.domain.classroom.entity.Status;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClassroomBoardResponse {
    private Room room;
    private String name;
    private Status status;
}