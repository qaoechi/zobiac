package com.jumjari.zobiac.global.security.principal;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.jumjari.zobiac.domain.member.entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Member implements OAuth2User {
    private final User user;
    private final Map<String, Object> attributes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(new SimpleGrantedAuthority(this.user.getRole().toString()));
    }
    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }
    @Override
    public String getName() {
        return this.user.getId().toString();
    }

    public Long getId() {
        return this.user.getId();
    }
    public String getNickName() {
        return this.user.getNickname();
    }
    public boolean isBlind() {
        return this.user.isBlind();
    }
    public boolean isCompleted() {
        return this.user.isCompleted();
    }
}