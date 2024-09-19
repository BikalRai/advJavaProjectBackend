package com.kickspot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kickspot.dto.UserRequestDTO;
import com.kickspot.dto.UserResponseDTO;
import com.kickspot.model.User;
import com.kickspot.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SimpMessagingTemplate messagingTemp;
	
	@PostMapping
	public ResponseEntity<String> addUser(@RequestBody UserRequestDTO userReqDTO) {
		messagingTemp.convertAndSend("/topic/users", userReqDTO);
		return userService.addUser(userReqDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/role/{role}")
	public ResponseEntity<List<User>> getUsersByRole(@PathVariable("role") String role) {
		return new ResponseEntity<>(userService.getUsersByRole(role), HttpStatus.OK);
	}
	
	@GetMapping("/mobile")
	public ResponseEntity<UserResponseDTO> getUserByEmail(@RequestParam("mobile") String mobile) {
		return ResponseEntity.ok(userService.getUserByMobile(mobile));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("id")  int id) {
		return userService.getUserById(id);
	}
	
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<String> updateUser(@PathVariable("id") int id, @RequestBody UserRequestDTO userReqDTO) {
		return userService.updateUser(id, userReqDTO);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
		return userService.deleteUserById(id);
	}
	
	
}
