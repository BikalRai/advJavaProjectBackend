package com.kickspot.model.otp;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Otp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String email;
	private String otp;
	private LocalDateTime otpExpiration;

	public Otp() {

	}

	public Otp(int id, String email, String otp, LocalDateTime otpExpiration) {

		this.id = id;
		this.email = email;
		this.otp = otp;
		this.otpExpiration = otpExpiration;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getOtpExpiration() {
		return otpExpiration;
	}

	public void setOtpExpiration(LocalDateTime otpExpiration) {
		this.otpExpiration = otpExpiration;
	}

}
