package com.jumjari.zobiac.application.classroom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.jumjari.zobiac.application.building.dto.BuildingDetail;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Room {
    private Long roomId;
    private BuildingDetail building;
    private String number;
    private Byte floor;
}