package com.jumjari.zobiac.application.member.dto;

public record ProfileRequest(
    String username,
    Integer number,
    String nickname
) {}