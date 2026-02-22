package com.jumjari.zobiac.infrastructure.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class KakaoTokenReponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("access_tpye")
    private String accessType;
    @JsonProperty("refresh_tpye")
    private String refreshType;
    @JsonProperty("expires_in")
    private int expiresIn;
}