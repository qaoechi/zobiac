package com.jumjari.zobiac.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.jumjari.zobiac.application.member.Member;
import com.jumjari.zobiac.application.member.service.OAuth2UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final OAuth2UserService oAuth2UserService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/home", "/member/**", "/oauth/**", "/css/**", "/js/**").permitAll()
            .requestMatchers("/admin/**").hasAnyRole("ADMIN")
            .requestMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER")
            .requestMatchers("/client/**").hasAnyRole("ADMIN", "MANAGER", "USER")
            .anyRequest().authenticated()
        )
        .oauth2Login(oauth -> oauth
            .loginPage("/member/login")
            .userInfoEndpoint(info -> info
                .userService(oAuth2UserService)
            )
            .successHandler((request, response, authentication) -> {
                Member member = (Member) authentication.getPrincipal();
                if (!member.isCompleted()) {
                    response.sendRedirect("/client/profile");
                } else {
                    response.sendRedirect("/home");
                }
            })
        )
        .logout(logout -> logout
            .logoutUrl("/member/logout")
            .logoutSuccessUrl("/home")
        );
        
        return http.build();
    }
}