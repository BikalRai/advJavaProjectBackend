package com.kickspot.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class PaymentDetailsResponseDTO {
	private String venueName;
	private String location;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	private long price;
	private String transactionId;

	public PaymentDetailsResponseDTO() {

	}

	public PaymentDetailsResponseDTO(String venueName, String location, LocalDate date, LocalTime startTime,
			LocalTime endTime, long price, String transactionId) {

		this.venueName = venueName;
		this.location = location;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.price = price;
		this.transactionId = transactionId;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}
