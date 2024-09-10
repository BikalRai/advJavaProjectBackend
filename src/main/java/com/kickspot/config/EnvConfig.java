package com.kickspot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class EnvConfig {
	
	@Bean
    String jwtSecret() {
        Dotenv dotenv = Dotenv.load();
        return dotenv.get("JWT_SECRET");
    }

}
