package com.kickspot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kickspot.model.User;
import com.kickspot.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private  UserRepository userRepo;
	
	public void registerUser(User user) {
		userRepo.save(user);
	}
	

	
	
}
