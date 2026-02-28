package com.jumjari.zobiac.application.member;

public record ProfileRequest(
    String username,
    Integer number,
    String nickname
) {}