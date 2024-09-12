package com.kickspot.model;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kickspot.model.booking.Booking;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
public class Venue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String location;
	private String description;
	private String amenities;
	private long price;
	@Lob
	private byte[] image;

	private LocalTime openingTime;
	private LocalTime closingTime;
	private int slotDurationMinutes;

//	@ManyToOne
//	@JoinColumn(name = "user_id")
//	private User owner;

	@OneToMany(mappedBy = "venueId")
	@JsonIgnore
	List<TimeSlot> timeSlots;

	@OneToMany(mappedBy = "venue")
	List<Booking> bookings;

	public Venue() {
	}

	public Venue(int id, String name, String location, String description, String amenities, long price, byte[] image,
			LocalTime openingTime, LocalTime closingTime, int slotDurationMinutes, List<TimeSlot> timeSlots,
			List<Booking> bookings) {

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

		this.timeSlots = timeSlots;
		this.bookings = bookings;
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

	public List<TimeSlot> getTimeSlots() {
		return timeSlots;
	}

	public void setTimeSlots(List<TimeSlot> timeSlots) {
		this.timeSlots = timeSlots;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	@Override
	public String toString() {
		return "Venue [id=" + id + ", name=" + name + ", location=" + location + ", description=" + description
				+ ", amenities=" + amenities + ", price=" + price + ", image=" + Arrays.toString(image)
				+ ", openingTime=" + openingTime + ", closingTime=" + closingTime + ", slotDurationMinutes="
				+ slotDurationMinutes + ", timeSlots=" + timeSlots + ", bookings=" + bookings + "]";
	}

}
