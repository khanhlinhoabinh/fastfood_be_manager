package com.fourctc.api_fastfood_manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // tắt CSRF để test dễ hơn
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // cho phép truy cập tất cả endpoint
                );
        return http.build();
    }
}
