package com.jumjari.zobiac.application.classroom.dto;

import com.jumjari.zobiac.domain.classroom.entity.Direction;
import com.jumjari.zobiac.domain.classroom.entity.DoorType;
import com.jumjari.zobiac.domain.classroom.entity.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Classroom {
    private Long id;
    private Room room;
    private String name;
    private Direction direction;
    private DoorType type;
    private Byte count;
    private Classroom parent;
    private String memo;
    private Status status;
}