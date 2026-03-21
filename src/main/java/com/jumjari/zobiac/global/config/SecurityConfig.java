package com.jumjari.zobiac.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.global.security.handler.LoginSuccessHandler;
import com.jumjari.zobiac.global.security.oauth.OAuth2UserService;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final OAuth2UserService oAuth2UserService;
    private final LoginSuccessHandler loginSuccessHandler;

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
            .successHandler(loginSuccessHandler)
        )
        .logout(logout -> logout
            .logoutUrl("/member/logout")
            .logoutSuccessUrl("/home")
        );
        
        return http.build();
    }
}