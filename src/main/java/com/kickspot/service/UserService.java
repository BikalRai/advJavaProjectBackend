package com.kickspot.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kickspot.dto.UserRequestDTO;
import com.kickspot.dto.UserResponseDTO;
import com.kickspot.model.Role;
import com.kickspot.model.User;
import com.kickspot.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleService roleService;
	
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
		user.setPassword(passwordEncoder.encode(userReqDTO.getPassword()));
		
		List<Role> roles = new ArrayList<>();
		
		if(userReqDTO.getRoleIds() != null &&  !userReqDTO.getRoleIds().isEmpty() ) {
			roles = roleService.getAllRolesById(userReqDTO.getRoleIds());
		}
		
		if(roles.isEmpty()) {
			Role defaultRole = roleService.getRoleByName("ROLE_USER");
			roles.add(defaultRole);
		}
		
		user.setRoles(roles);
		
		userRepo.save(user);
		
		return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);
	}

	
	public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
		
		return new ResponseEntity<>(userRepo.findAllUsers(), HttpStatus.OK);
	}

	public UserResponseDTO getUserByMobile(String mobile) {
		UserResponseDTO userRes = userRepo.getByMobile(mobile);
		
		if (userRes == null) {
			return null;
		}
		
		return userRes;
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
		
		byte[] imageBytes = Base64.getDecoder().decode(userReqDTO.getImage());
		user.setImage(imageBytes);
		
		if(!(userReqDTO.getPassword() == null) && !userReqDTO.getPassword().isEmpty()) {
			user.setPassword(passwordEncoder.encode(userReqDTO.getPassword()));
		}
		
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
