package com.kickspot.model.booking;

import java.time.LocalDate;
import java.time.LocalTime;

import com.kickspot.model.TimeSlot;
import com.kickspot.model.User;
import com.kickspot.model.Venue;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate bookingDate;
	private long price;
	private String status;

	@ManyToOne
	@JoinColumn(name = "time_slot_id")
	private TimeSlot timeSlot;

	@ManyToOne
	@JoinColumn(name = "venue_id")
	private Venue venue;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Booking() {
	}

	public Booking(int id, LocalDate bookingDate, long price, String status, TimeSlot timeSlot, Venue venue,
			User user) {
		super();
		this.id = id;
		this.bookingDate = bookingDate;
		this.price = price;
		this.status = status;
		this.timeSlot = timeSlot;
		this.venue = venue;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TimeSlot getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
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

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", bookingDate=" + bookingDate + ", price=" + price + ", status=" + status
				+ ", timeSlot=" + timeSlot + ", venue=" + venue + ", user=" + user + "]";
	}

}
