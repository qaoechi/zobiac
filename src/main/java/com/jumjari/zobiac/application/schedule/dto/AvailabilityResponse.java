package com.jumjari.zobiac.application.schedule.dto;

import com.jumjari.zobiac.domain.schedule.entity.Week;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvailabilityResponse {
    private Long id;
    private Week week;
    private Integer slot;
}