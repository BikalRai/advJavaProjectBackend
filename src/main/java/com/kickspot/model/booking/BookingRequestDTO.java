package com.kickspot.model.booking;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingRequestDTO {

	private int id;
	private LocalTime startTime;
	private LocalTime endTime;
	private LocalDate bookingDate;
	private long price;
	private String status;
}
