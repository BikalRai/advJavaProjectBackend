package com.kickspot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.kickspot.custom.CustomUserDetailsService;
import com.kickspot.jwt.config.JwtAuthfilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	@Autowired
	private JwtAuthfilter jwtAuthfilter;
	
	
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(requests -> requests
					.requestMatchers("/api/v1/auth/**").permitAll()
					.requestMatchers("/ws/**").permitAll()
					.requestMatchers("/api/password/**").permitAll()
					
					.requestMatchers(HttpMethod.GET, "/api/services/**", "/api/venues/**").permitAll()
					
					.requestMatchers(HttpMethod.GET, "/api/users/**").hasAnyRole("USER", "ADMIN")
					.requestMatchers(HttpMethod.POST, "/api/users/**").hasAnyRole("USER", "ADMIN")
					.requestMatchers(HttpMethod.PUT, "/api/users/**").hasAnyRole("USER", "ADMIN")
					.requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")
					
					.requestMatchers("/api/bookings/**").hasAnyRole("USER", "ADMIN")
					
					.requestMatchers(HttpMethod.POST, "/api/venues/{venueId}/timeslots").hasRole("USER")
					
					.requestMatchers(HttpMethod.GET, "/api/time/**").hasAnyRole("USER", "ADMIN")
					.requestMatchers(HttpMethod.GET, "/api/payment/**").hasAnyRole("USER", "ADMIN")
					
					
					.requestMatchers("/**").hasRole("ADMIN")
					.anyRequest()
					.authenticated()
			)
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authenticationProvider(authenticationProvider())
			.addFilterBefore(jwtAuthfilter, UsernamePasswordAuthenticationFilter.class)
			.cors();
		
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(customUserDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
