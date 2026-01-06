package com.TaskManagement1.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfguration {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeHttpRequests(auth -> auth
				.requestMatchers()
				.authenticated().anyRequest().permitAll());
		return http.build();
	}
	

}
