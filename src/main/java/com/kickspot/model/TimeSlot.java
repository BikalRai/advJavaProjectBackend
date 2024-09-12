package com.kickspot.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.kickspot.model.booking.Booking;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class TimeSlot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate date; // this will hold the date
	private LocalTime startTime; // this will hold the time
	private LocalTime endTime; // this will hold the time
	private boolean available;

	@ManyToOne
	@JoinColumn(name = "venue_id")
	private Venue venueId;

	@OneToMany(mappedBy = "timeSlot")
	private List<Booking> bookingId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Venue getVenueId() {
		return venueId;
	}

	public void setVenueId(Venue venueId) {
		this.venueId = venueId;
	}

	public List<Booking> getBookingId() {
		return bookingId;
	}

	public void setBookingId(List<Booking> bookingId) {
		this.bookingId = bookingId;
	}

}
