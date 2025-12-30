package com.jumjari.zobiac.application.building.dto;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Building {
    private Long id;
    private String korFull;
    private boolean campus;
    private String korShort;
    private String engShort;
    private Double latitude;
    private Double longitude;
}