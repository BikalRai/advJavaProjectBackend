package com.kickspot.controller.jwtAuthController;

public class AuthRegistrationRequest {

	private String username;
	private String mobile;
	private String email;
	private String password;

	public AuthRegistrationRequest() {

	}

	public AuthRegistrationRequest(String username, String mobile, String email, String password) {
		
		this.username = username;
		this.mobile = mobile;
		this.email = email;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

}
