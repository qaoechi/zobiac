package com.jumjari.zobiac.application.building.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BuildingDetail {
    private Long id;
    private String korFull;
    private boolean campus;
    private String korShort;
}