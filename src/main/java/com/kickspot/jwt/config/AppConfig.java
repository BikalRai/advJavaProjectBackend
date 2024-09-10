package com.kickspot.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kickspot.custom.CustomUserDetailsService;


//public class AppConfig {
//	
//	@Autowired
//	private CustomUserDetailsService customUserDetailsService;
//	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	
//	@Bean
//	AuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(customUserDetailsService);
//		authProvider.setPasswordEncoder(passwordEncoder);
//		
//		return authProvider;
//	}
//	
//	@Bean
//	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//		return config.getAuthenticationManager();
//	}
//
//}
