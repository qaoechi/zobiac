package com.jumjari.zobiac.application.member;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.domain.member.User;

@RequiredArgsConstructor
public class Member implements UserDetails {
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(new SimpleGrantedAuthority(this.user.getRole().toString()));
    }
    @Override
    public String getPassword() {
        return null;
    }
    @Override
    public String getUsername() {
        return this.user.getUsername();
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
}