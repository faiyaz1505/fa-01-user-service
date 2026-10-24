package com.fa.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fa.user.filter.JwtFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    public SecurityConfig(JwtFilter jwtFilter) {
		this.jwtFilter=jwtFilter;
	}

    @Bean
    SecurityFilterChain security(
            HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/public/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS))
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
