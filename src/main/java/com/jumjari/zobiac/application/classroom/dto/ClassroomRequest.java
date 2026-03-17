package com.jumjari.zobiac.application.classroom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClassroomRequest {
    private Long id;
    private Long building;
    private String number;
    private Boolean floor;
    private String name;
    private String direction;
    private Integer doorType;
    private Byte count;
    private Long parentId;
    private String memo;
}