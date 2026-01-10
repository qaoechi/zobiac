package com.jumjari.zobiac.infrastructure.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import com.jumjari.zobiac.application.member.Member;

@Component
public class JwtProvider {
    private final Key key;
    private final long time;
    
    public JwtProvider (
        @Value("${jwt.secret}") String secretKey,
        @Value("${jwt.expires}") Long time
    ) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.time = time;
    }

    public String createToken(Member member) {
        Date now = new Date();
        Date expiredAt = new Date(now.getTime() + time);

        return Jwts.builder()
            .setSubject(String.valueOf(member.getId()))
            .claim("role", member.getAuthorities().iterator().next().getAuthority())
            .claim("blind", member.isBlind())
            .setIssuedAt(now)
            .setExpiration(expiredAt)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }
}