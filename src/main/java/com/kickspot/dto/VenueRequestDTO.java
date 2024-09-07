package com.kickspot.dto;

import java.time.LocalTime;
import java.util.List;

import com.kickspot.model.TimeSlot;
import com.kickspot.model.User;
import com.kickspot.model.booking.Booking;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class VenueRequestDTO {
	private int id;
	private String name;
	private String location;
	private String description;
	private String amenities;
	private long price;
	private byte[] image;

	private LocalTime openingTime;
	private LocalTime closingTime;
	private int slotDurationMinutes;

	private int ownerId;

	public VenueRequestDTO() {

	}

	public VenueRequestDTO(int id, String name, String location, String description, String amenities, long price,
			byte[] image, LocalTime openingTime, LocalTime closingTime, int slotDurationMinutes, int ownerId) {
	
		this.id = id;
		this.name = name;
		this.location = location;
		this.description = description;
		this.amenities = amenities;
		this.price = price;
		this.image = image;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.slotDurationMinutes = slotDurationMinutes;
		this.ownerId = ownerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public LocalTime getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(LocalTime openingTime) {
		this.openingTime = openingTime;
	}

	public LocalTime getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(LocalTime closingTime) {
		this.closingTime = closingTime;
	}

	public int getSlotDurationMinutes() {
		return slotDurationMinutes;
	}

	public void setSlotDurationMinutes(int slotDurationMinutes) {
		this.slotDurationMinutes = slotDurationMinutes;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

}
