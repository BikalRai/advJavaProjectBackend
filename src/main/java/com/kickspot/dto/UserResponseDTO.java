package com.kickspot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public interface UserResponseDTO {
	

	int getId();


	public String getFirstName();

	public String getLastName();

	public String getEmail();

	public String getMobile();

	

}
