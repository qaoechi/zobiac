package com.jumjari.zobiac.application.schedule.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.jumjari.zobiac.domain.member.User;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Meeting {
    private Long id;
    private String title;
    private String description;
    private boolean open;
    private User createdBy;
    private List<Participant> participants;
}