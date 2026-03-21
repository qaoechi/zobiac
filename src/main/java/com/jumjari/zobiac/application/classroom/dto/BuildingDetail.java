package com.jumjari.zobiac.application.classroom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BuildingDetail {
    private Long id;
    private String korFull;
    private boolean campus;
    private String korShort;
}