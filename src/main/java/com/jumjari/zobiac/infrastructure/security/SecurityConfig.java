package com.jumjari.zobiac.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/home", "/member/**", "/oauth/**", "/css/**", "/js/**").permitAll()
            .requestMatchers("/admin/**").hasAnyRole("ADMIN")
            .requestMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER")
            .requestMatchers("/client/**").hasAnyRole("ADMIN", "MANAGER", "USER")
            .anyRequest().authenticated()
        )
        .formLogin(form -> form.disable());
        
        return http.build();
    }
}