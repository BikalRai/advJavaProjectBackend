package com.kickspot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kickspot.model.User;
import com.kickspot.model.otp.OtpService;
import com.kickspot.model.otp.OtpVerificationRequest;
import com.kickspot.model.password.EmailRequest;
import com.kickspot.model.password.PasswordReq;
import com.kickspot.repository.UserRepository;
import com.kickspot.service.MailService;

@RestController
@RequestMapping("/api/password")
public class PasswordResetResController {
	
	@Autowired
	private OtpService otpService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/reset-request")
	public ResponseEntity<?> requestPasswordReset(@RequestBody EmailRequest emailReq) {
		Optional<User> user = userRepo.findByEmail(emailReq.getEmail());
		
		if(!user.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		String otp = otpService.generateOtp(6);
		otpService.saveOtp(emailReq.getEmail(), otp);
		
		mailService.sendOtp(emailReq.getEmail(), otp);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/verify-otp")
	public ResponseEntity<?> verifyOtp(@RequestBody OtpVerificationRequest otpReq) {
		boolean otpIsValid = otpService.verifyOtp(otpReq.getEmail(), otpReq.getOtp());
		
		if (!otpIsValid) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>("",HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/reset")
	public ResponseEntity<?> resetPassword(@RequestBody PasswordReq passwordReq) {
		
		Optional<User> userObj = userRepo.findByEmail(passwordReq.getEmail());
		
		if(!userObj.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		User user = userObj.get();
		user.setPassword(passwordEncoder.encode(passwordReq.getPassword()));
		userRepo.save(user);
		
		mailService.sendPasswordResetMessage(user.getEmail());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
