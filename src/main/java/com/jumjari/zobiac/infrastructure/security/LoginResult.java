package com.jumjari.zobiac.infrastructure.security;

public record LoginResult (
    String access,
    String refresh
) {}