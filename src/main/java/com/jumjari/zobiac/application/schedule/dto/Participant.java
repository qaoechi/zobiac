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
public class Participant {
    private Long id;
    private Meeting meeting;
    private User user;
    private String name;
    private String token;
    private List<Availability> availabilities;
}