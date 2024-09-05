package com.kickspot.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

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
	private LocalTime startTime;
	private LocalTime endTime;
	private LocalDate bookingDate;
	private long price;
	private String status;

	@ManyToOne
	@JoinColumn(name = "venue_id")
	private Venue venue;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Booking() {
	}

	public Booking(int id, LocalTime startTime, LocalTime endTime, LocalDate bookingDate, long price, String status,
			Venue venue, User user) {

		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.bookingDate = bookingDate;
		this.price = price;
		this.status = status;
		this.venue = venue;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return "Booking [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", bookingDate="
				+ bookingDate + ", price=" + price + ", status=" + status + ", venue=" + venue + ", user=" + user + "]";
	}

	

}
