package com.kickspot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kickspot.dto.UserRequestDTO;
import com.kickspot.dto.UserResponseDTO;
import com.kickspot.model.User;
import com.kickspot.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	

	
	public ResponseEntity<String> addUser(UserRequestDTO userReqDTO) {
		if(userRepo.existsByEmail(userReqDTO.getEmail())) {
			return new ResponseEntity<>("Email already taken", HttpStatus.BAD_REQUEST);
		}
		
		if (userRepo.existsByMobile(userReqDTO.getMobile())) {
			return new ResponseEntity<>("Mobile already taken", HttpStatus.BAD_REQUEST);
		}
		
		User user = new User();
		user.setFirstName(userReqDTO.getFirstName());
		user.setLastName(userReqDTO.getLastName());
		user.setEmail(userReqDTO.getEmail());
		user.setMobile(userReqDTO.getMobile());
		user.setPassword(userReqDTO.getPassword());
		
		userRepo.save(user);
		
		return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);
	}

	
	public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
		
		return new ResponseEntity<>(userRepo.findAllUsers(), HttpStatus.OK);
	}

	
	public ResponseEntity<UserResponseDTO> getUserById(int id) {
		
		UserResponseDTO user = userRepo.getUserById(id);
		
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	
	public ResponseEntity<String> updateUser(int id, UserRequestDTO userReqDTO) {
		Optional<User> existingUser = userRepo.findById(id);
		
		if (!existingUser.isPresent()) {
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		}
		
		User user = existingUser.get();
		
		user.setFirstName(userReqDTO.getFirstName());
		user.setLastName(userReqDTO.getLastName());
		user.setEmail(userReqDTO.getEmail());
		user.setMobile(userReqDTO.getMobile());
		
		userRepo.save(user);
		
		return new ResponseEntity<>("Successfully updated", HttpStatus.OK);		
		
	}


	public ResponseEntity<String> deleteUserById(int id) {
		Optional<User> existingUser = userRepo.findById(id);
		
		if(!existingUser.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		User user = existingUser.get();
		userRepo.delete(user);
		return new ResponseEntity<>("User deleted with id: " + id, HttpStatus.OK);
	}


	
}
