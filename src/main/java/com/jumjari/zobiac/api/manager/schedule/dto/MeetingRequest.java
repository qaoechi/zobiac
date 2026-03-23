package com.jumjari.zobiac.api.manager.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MeetingRequest {
    private String title;
    private String description;
    private boolean open;
}