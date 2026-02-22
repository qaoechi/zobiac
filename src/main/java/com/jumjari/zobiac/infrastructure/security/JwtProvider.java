package com.jumjari.zobiac.infrastructure.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import com.jumjari.zobiac.application.member.Member;

@Component
public class JwtProvider {
    private final Key key;
    private final long access;

    public JwtProvider (
        @Value("${jwt.secret}") String secretKey,
        @Value("${jwt.access}") long access
    ) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.access = access;
    }

    public String createAccessToken(Member member) {
        Date now = new Date();
        Date expiredAt = new Date(now.getTime() +  access);

        JwtBuilder builder = Jwts.builder()
            .setSubject(String.valueOf(member.getId()))
            .claim("role", member.getAuthorities().iterator().next().getAuthority())
            .claim("blind", member.isBlind())
            .setIssuedAt(now)
            .setExpiration(expiredAt);
        return builder.signWith(key, SignatureAlgorithm.HS256).compact();
    }

    public Long getUserId(String token) {
        return Long.parseLong(
            Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject()
        );
    }
    public TokenType getTokenType(String token) {
        String type = (String)Jwts.parserBuilder().setSigningKey(key).build()
            .parseClaimsJws(token)
            .getBody()
            .get("type");
        return TokenType.valueOf(type);
    }
}