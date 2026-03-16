package com.jumjari.zobiac.application.classroom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.jumjari.zobiac.application.building.dto.Building;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Room {
    private Long roomId;
    private Building building;
    private String number;
    private Byte floor;
}