package com.jumjari.zobiac.global.security.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.jumjari.zobiac.global.security.principal.Member;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication
    ) throws IOException, ServletException {
        Member member = (Member) authentication.getPrincipal();
        if (!member.isCompleted()) {
            response.sendRedirect("/client/profile");
        } else {
            response.sendRedirect("/home");
        }
    }
}