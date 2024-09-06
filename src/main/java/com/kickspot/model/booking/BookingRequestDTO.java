package com.kickspot.model.booking;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingRequestDTO {

	private int id;
	private LocalDate bookingDate;
	private long price;
	private String status;
	private int timeSlotId;
	private int userId;
	private int venueId;

	public BookingRequestDTO() {

	}

	public BookingRequestDTO(int id, LocalDate bookingDate, long price, String status, int timeSlotId, int userId,
			int venueId) {

		this.id = id;
		this.bookingDate = bookingDate;
		this.price = price;
		this.status = status;
		this.timeSlotId = timeSlotId;
		this.userId = userId;
		this.venueId = venueId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTimeSlotId() {
		return timeSlotId;
	}

	public void setTimeSlotId(int timeSlotId) {
		this.timeSlotId = timeSlotId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getVenueId() {
		return venueId;
	}

	public void setVenueId(int venueId) {
		this.venueId = venueId;
	}

}
