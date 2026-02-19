package com.jumjari.zobiac.infrastructure.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.member.Member;
import com.jumjari.zobiac.domain.member.User;
import com.jumjari.zobiac.domain.member.UserRepository;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider provider;
    private final UserRepository repository;
    
    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        String token = "";
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("access_token")) {
                    token = cookie.getValue();
                }
            }
        }

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            Long userId = provider.getUserId(token);
            User user = repository.findById(userId).orElseThrow();

            Member member = new Member(user);

            Authentication auth = new UsernamePasswordAuthenticationToken(
                member,
                null,
                member.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (Exception e) {}
        filterChain.doFilter(request, response);
    }
}