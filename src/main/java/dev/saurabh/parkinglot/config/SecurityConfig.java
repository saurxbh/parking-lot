package dev.saurabh.parkinglot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity in Postman
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()  // Allow all requests
                )
                .httpBasic(); // Still allow basic auth (optional)

        return http.build();
    }
}