package com.kickspot.custom;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.kickspot.model.User;
import com.kickspot.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> userOp = userRepository.findByEmail(username);
		
		if(!userOp.isPresent()) {
			userOp = userRepository.findByMobile(username);
		}
		
		User user = userOp.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
		return new CustomUserDetails(user);
	}

}
