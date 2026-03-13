package com.jumjari.zobiac.application.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MeetingResponse {
    private Long id;
    private String title;
    private String description;
    private boolean open;
}