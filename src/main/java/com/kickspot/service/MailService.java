package com.kickspot.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender jms;
	
	public void sendBookingConfirmationEmail(
			String to,
			LocalTime startTime,
			LocalTime endTime,
			LocalDate date,
			String venueName
			) {
		SimpleMailMessage msg = new SimpleMailMessage();
		
		StringBuilder string = new StringBuilder();
		string
			.append("Your booking has been confirmed from\n")
			.append(startTime + " to " + endTime + "\n")
			.append("for: " + date + "\n")
			.append("VENUE: " + venueName);
		
		
		
		msg.setTo(to);
		msg.setSubject("Your booking has been confirmed for date: " + date);
		msg.setText(string.toString());
		
		jms.send(msg);
	}
	
	public void sendOtp(String to, String otp) {
		SimpleMailMessage msg = new SimpleMailMessage();
		
		StringBuilder string = new StringBuilder();
				string
				.append("Your OTP to reset your password is:\n")
				.append(otp + "\n")
				.append("Please do not share it with anyone\n")
				.append("It is only valid for 5 minutes");
				
		
		msg.setTo(to);
		msg.setSubject("KickSpot OTP");
		msg.setText(string.toString());
		
		jms.send(msg);
	}
}
