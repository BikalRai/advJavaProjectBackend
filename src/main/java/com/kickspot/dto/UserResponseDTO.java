package com.kickspot.dto;

import java.time.LocalDate;

//@JsonInclude(Include.NON_NULL)
public interface UserResponseDTO {

	int getId();

	String getFirstName();

	String getLastName();

	String getEmail();

	String getMobile();

	byte[] getImage();
	
	LocalDate getCreatedDate();

}
