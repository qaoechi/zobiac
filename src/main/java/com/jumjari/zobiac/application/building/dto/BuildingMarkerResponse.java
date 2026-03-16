package com.jumjari.zobiac.application.building.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BuildingMarkerResponse {
    private Long id;
    private boolean campus;
    private String korShort;
    private String engShort;
    private Double latitude;
    private Double longitude;
}