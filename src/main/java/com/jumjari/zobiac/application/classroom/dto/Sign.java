package com.jumjari.zobiac.application.classroom.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sign {
    private Long id;
    private String number;
    private String placard;
    private String front;
    private String back;
    private String other;
    private String memo;
}