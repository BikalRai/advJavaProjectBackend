package com.kickspot.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kickspot.model.TimeSlot;
import com.kickspot.model.User;
import com.kickspot.model.Venue;
import com.kickspot.model.booking.Booking;
import com.kickspot.model.booking.BookingRequestDTO;
import com.kickspot.repository.BookingRepository;
import com.kickspot.repository.TimeSlotRepository;
import com.kickspot.repository.UserRepository;
import com.kickspot.repository.VenueRepository;

@Service
public class BookingService {
	
	@Autowired
	private BookingRepository bookingRepo;
	
	@Autowired
	private TimeSlotRepository timeSlotRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private VenueRepository venueRepo;
	
	public ResponseEntity<String> createBooking(BookingRequestDTO bookingReqDTO) {
		Optional<TimeSlot> timeSlotExists = timeSlotRepo.findById(bookingReqDTO.getTimeSlotId());
		if(!timeSlotExists.isPresent()) {
			return new ResponseEntity<>("Time slot not found", HttpStatus.NOT_FOUND);
		}
		
		Optional<User> userExists = userRepo.findById(bookingReqDTO.getUserId());
		if(!userExists.isPresent()) {
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		}
		
		Optional<Venue> venueExists = venueRepo.findById(bookingReqDTO.getVenueId());
		if(!venueExists.isPresent()) {
			return new ResponseEntity<>("Venue not found", HttpStatus.NOT_FOUND);
		}
		
		TimeSlot timeSlot = timeSlotExists.get();
		User user = userExists.get();
		Venue venue = venueExists.get();
		
		if(!timeSlot.isAvailable()) {
			return new ResponseEntity<>("Time slot not available", HttpStatus.BAD_REQUEST);
		}
		
		LocalDate date = LocalDate.now();
		
		Booking booking = new Booking();
		booking.setBookingDate(date);
		booking.setPrice(bookingReqDTO.getPrice());
		booking.setStatus(bookingReqDTO.getStatus());
		booking.setUser(user);
		booking.setTimeSlot(timeSlot);
		booking.setVenue(venue);
		
		bookingRepo.save(booking);
		
		return new ResponseEntity<>("Booking successfully created", HttpStatus.CREATED);
		
		
	}
	
}
