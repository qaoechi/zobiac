package com.jumjari.zobiac.application.classroom.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import com.jumjari.zobiac.application.room.dto.Room;
import com.jumjari.zobiac.application.direction.dto.Direction;
import com.jumjari.zobiac.application.door_type.dto.DoorType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Classroom {
    private Long id;
    private boolean isActive;
    private Room room;
    private String name;
    private Direction direction;
    private DoorType type;
    private Byte count;
    private Classroom parent;
    private String memo;
}