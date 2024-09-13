package com.kickspot.model.booking;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kickspot.model.TimeSlot;
import com.kickspot.model.User;
import com.kickspot.model.Venue;

@JsonInclude(Include.NON_NULL)
public class BookingResponseDTO {
	private final int id;
	private final LocalDate bookingDate;
	private final long price;
	private final boolean isCompleted;
	private final TimeSlot timeSlot;
	private final Venue venue;
	private final User user;

	public BookingResponseDTO(Booking booking) {
		this.id = booking.getId();
		this.bookingDate = booking.getBookingDate();
		this.price = booking.getPrice();
		this.isCompleted = booking.isCompleted();
		this.timeSlot = booking.getTimeSlot();
		this.venue = booking.getVenue();
		this.user = booking.getUser();
	}

	public int getId() {
		return id;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public long getPrice() {
		return price;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public TimeSlot getTimeSlot() {
		return timeSlot;
	}

	public Venue getVenue() {
		return venue;
	}

	public User getUser() {
		return user;
	}

}
