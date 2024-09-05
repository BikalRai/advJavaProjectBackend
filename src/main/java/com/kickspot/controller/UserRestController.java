package com.kickspot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kickspot.dto.UserRequestDTO;
import com.kickspot.dto.UserResponseDTO;
import com.kickspot.service.UserService;

@RestController
//@RequestMapping("/api")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("id")  int id) {
		return userService.getUserById(id);
	}
	
	@PutMapping("/updateUSer")
	public ResponseEntity<String> updateUser(@RequestParam("id") int id, @RequestBody UserRequestDTO userReqDTO) {
		return userService.updateUser(id, userReqDTO);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
		return userService.deleteUserById(id);
	}
	
	
}
