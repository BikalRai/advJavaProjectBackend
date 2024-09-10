package com.kickspot.controller.jwtAuthController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class JwtAuthController {
	
	@Autowired
	private JwtAuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<JwtAuthResponse> register (
			@RequestBody AuthRegistrationRequest request
			) {
		return ResponseEntity.ok(authService.register(request));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<JwtAuthResponse> authenticate (
			@RequestBody JwtAuthRequest request
			) {
		return ResponseEntity.ok(authService.authenticate(request));
	}
	
}
