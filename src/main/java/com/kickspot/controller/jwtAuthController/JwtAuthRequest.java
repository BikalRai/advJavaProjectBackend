package com.kickspot.controller.jwtAuthController;

public class JwtAuthRequest {

	private String emailOrMobile;
	private String password;

	public JwtAuthRequest() {

	}

	public JwtAuthRequest(String emailOrMobile, String password) {

		this.emailOrMobile = emailOrMobile;
		this.password = password;
	}

	public String getEmailOrMobile() {
		return emailOrMobile;
	}

	public void setEmailOrMobile(String emailOrMobile) {
		this.emailOrMobile = emailOrMobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "JwtAuthRequest [emailOrMobile=" + emailOrMobile + ", password=" + password + "]";
	}

}
