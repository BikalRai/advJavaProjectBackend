package com.kickspot.model.otp;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

	@Autowired
	private OtpRepository otpRepo;
	
	public String generateOtp(int length) {
		Random random = new Random();
		
		StringBuilder otp = new StringBuilder();
		
		for (int i = 0; i < length; i++) {
			otp.append(random.nextInt(10));
		}
		
		return otp.toString();
	}
	
	public void saveOtp(String email, String otp) {
		Otp otpObj = new Otp();
		otpObj.setOtp(otp);
		otpObj.setEmail(email);
		otpObj.setOtpExpiration(LocalDateTime.now().plusMinutes(5));
		
		otpRepo.save(otpObj);
	}
	
	public boolean verifyOtp(String email, String otp) {
		Otp otpObj = otpRepo.findByEmail(email);
		
		if(otpObj == null || otpObj.getOtpExpiration().isBefore(LocalDateTime.now())) {
			return false;
		}
		
		return otpObj.getOtp().equals(otp);
	}
	
}
