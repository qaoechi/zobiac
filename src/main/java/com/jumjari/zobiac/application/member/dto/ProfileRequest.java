package com.jumjari.zobiac.application.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfileRequest{
    private String username;
    private Integer number;
    private String nickname;
}