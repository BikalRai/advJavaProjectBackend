package com.kickspot.model.booking;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kickspot.model.TimeSlot;
import com.kickspot.model.Venue;

@JsonInclude(Include.NON_NULL)
public interface BookingResponseDTO {
	int getId();
	
	LocalDate getBookingDate();
	
	long getPrice();
	
	String getStatus();
	
	TimeSlot getTimeSlot();
	
	Venue getVenue();
}
