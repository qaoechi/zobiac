package com.jumjari.zobiac.application.schedule.dto;

import com.jumjari.zobiac.domain.schedule.entity.Week;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AvailabilityRequest {
    private Week week;
    private Integer slot;
}