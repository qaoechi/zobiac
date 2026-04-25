package com.jumjari.zobiac.application.classroom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClassroomSign {
    private Long id;
    private String number;
    private String placard;
    private String front;
    private String back;
    private String other;
    private String memo;

    @Override
    public String toString() {
        return placard + " " + nvl(front) + nvl(back);
    }
    private String nvl(String s) {
        return (s == null) ? "" : s;
    }
}