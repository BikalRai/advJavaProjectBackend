package com.kickspot.controller.jwtAuthController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kickspot.custom.CustomUserDetailsService;
import com.kickspot.jwt.config.JwtUtils;
import com.kickspot.model.Role;
import com.kickspot.model.User;
import com.kickspot.repository.RoleRepository;
import com.kickspot.repository.UserRepository;

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
	private RoleRepository roleRepository;
	
	@Autowired
	private CustomUserDetailsService customUserDetailService;
	
	public JwtAuthResponse register(AuthRegistrationRequest request) {
		
		
		User user = new User();
		user.setEmail(request.getEmail());
		user.setMobile(request.getMobile());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		
		List<Role> defaultRoles = new ArrayList<>();
		Role role;
		
		List<User> users = userRepo.findAll();
		
		if(users.size() == 0) {
			Optional<Role> roleExists = roleRepository.findByName("ROLE_ADMIN");
			if(roleExists.isPresent()) {
				role = roleExists.get();
				defaultRoles.add(role);
			}
		} else {
			Optional<Role> roleExists = roleRepository.findByName("ROLE_USER");
			if(roleExists.isPresent()) {
				role = roleExists.get();
				defaultRoles.add(role);
			}
		}
		
		
		
		user.setRoles(defaultRoles);
		
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
