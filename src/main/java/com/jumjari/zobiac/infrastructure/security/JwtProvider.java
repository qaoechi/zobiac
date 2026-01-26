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
    private final long refresh;        

    public JwtProvider (
        @Value("${jwt.secret}") String secretKey,
        @Value("${jwt.access}") long access,
        @Value("${jwt.refresh}") long refresh
    ) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.access = access;
        this.refresh = refresh;
    }

    public String createToken(Member member, TokenType type) {
        Date now = new Date();
        Date expiredAt = new Date(now.getTime() + (type == TokenType.ACCESS ? access : refresh));

        JwtBuilder builder = Jwts.builder()
            .setSubject(String.valueOf(member.getId()))
            .claim("type", type.toString())
            .setIssuedAt(now)
            .setExpiration(expiredAt);
            
        if (type == TokenType.ACCESS) {
            builder.claim("role", member.getAuthorities().iterator().next().getAuthority())
                .claim("blind", member.isBlind());
        }
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