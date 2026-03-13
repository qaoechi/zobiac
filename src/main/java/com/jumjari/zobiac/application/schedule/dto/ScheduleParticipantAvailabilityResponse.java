package com.jumjari.zobiac.application.schedule.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScheduleParticipantAvailabilityResponse {
    private Long participantId;
    private String name;
    private List<AvailabilityResponse> availabilities;
}