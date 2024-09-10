package com.kickspot.controller.jwtAuthController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kickspot.custom.CustomUserDetailsService;
import com.kickspot.jwt.config.JwtUtils;
import com.kickspot.model.User;
import com.kickspot.repository.UserRepository;
import com.kickspot.service.UserService;

@Service
public class JwtAuthService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomUserDetailsService customUserDetailService;
	
	public JwtAuthResponse register(AuthRegistrationRequest request) {
		String role;
		
		User user = new User();
		user.setEmail(request.getEmail());
		user.setMobile(request.getMobile());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRoles(null);
		
		userRepo.save(user);
		
		UserDetails userDetails = customUserDetailService.loadUserByUsername(user.getEmail());
		
		String token = jwtUtils.generateToken(userDetails);
		
		return new JwtAuthResponse(token);
	}
	
	public JwtAuthResponse authenticate(JwtAuthRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmailOrMobile(), request.getPassword()));
		UserDetails userDetails = customUserDetailService.loadUserByUsername(request.getEmailOrMobile());
		
		String token = jwtUtils.generateToken(userDetails);
		
		return new JwtAuthResponse(token);
	}
}
