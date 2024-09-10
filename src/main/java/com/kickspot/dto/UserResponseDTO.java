package com.kickspot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

//@JsonInclude(Include.NON_NULL)
public interface UserResponseDTO {

	int getId();

	String getFirstName();

	String getLastName();

	String getEmail();

	String getMobile();

	byte[] getImage();

}
