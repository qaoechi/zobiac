package com.jumjari.zobiac.infrastructure.security;

import com.jumjari.zobiac.domain.refresh_token.RefreshToken;

public record LoginResult (
    String access,
    RefreshToken refresh
) {}