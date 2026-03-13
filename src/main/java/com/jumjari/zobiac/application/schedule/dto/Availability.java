package com.jumjari.zobiac.application.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.jumjari.zobiac.domain.schedule.entity.Week;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Availability {
    private Long id;
    private Participant participant;
    private Week week;
    private Integer slot;
}