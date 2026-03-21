package com.jumjari.zobiac.application.classroom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BuildingMarkerResponse {
    private Long id;
    private boolean campus;
    private String korFull;
    private String engShort;
    private Double latitude;
    private Double longitude;
}