package com.kickspot.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.kickspot.model.TimeSlot;
import com.kickspot.model.User;
import com.kickspot.model.Venue;
import com.kickspot.model.booking.Booking;
import com.kickspot.model.booking.BookingRequestDTO;
import com.kickspot.model.booking.BookingResponseDTO;
import com.kickspot.repository.BookingRepository;
import com.kickspot.repository.TimeSlotRepository;
import com.kickspot.repository.UserRepository;
import com.kickspot.repository.VenueRepository;

import jakarta.transaction.Transactional;

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
	
	@Autowired
	private MailService mailService;

	public ResponseEntity<String> createBooking(BookingRequestDTO bookingReqDTO) {
		Optional<TimeSlot> timeSlotExists = timeSlotRepo.findById(bookingReqDTO.getTimeSlotId());
		if (!timeSlotExists.isPresent()) {
			return new ResponseEntity<>("Time slot not found", HttpStatus.NOT_FOUND);
		}

		Optional<User> userExists = userRepo.findById(bookingReqDTO.getUserId());
		if (!userExists.isPresent()) {
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		}

		Optional<Venue> venueExists = venueRepo.findById(bookingReqDTO.getVenueId());
		if (!venueExists.isPresent()) {
			return new ResponseEntity<>("Venue not found", HttpStatus.NOT_FOUND);
		}

		TimeSlot timeSlot = timeSlotExists.get();
		User user = userExists.get();
		Venue venue = venueExists.get();

		if (!timeSlot.isAvailable()) {
			return new ResponseEntity<>("Time slot not available", HttpStatus.BAD_REQUEST);
		}

		timeSlot.setAvailable(false);
	

		Booking booking = new Booking();
		booking.setBookingDate(bookingReqDTO.getBookingDate());
		booking.setPrice(bookingReqDTO.getPrice());
		booking.setCompleted(bookingReqDTO.isStatus());
		booking.setUser(user);
		booking.setTimeSlot(timeSlot);
		booking.setVenue(venue);

		bookingRepo.save(booking);
		
		mailService.sendBookingConfirmationEmail(user.getEmail(), timeSlot.getStartTime(),
				timeSlot.getEndTime(), timeSlot.getDate(), venue.getName());

		return new ResponseEntity<>("Booking successfully created", HttpStatus.CREATED);

	}
	
	public List<Booking> showAllBookings() {
		return bookingRepo.findAll();
	}
	
	@Transactional
    public List<BookingResponseDTO> showUserBookings(int userId) {
        List<Booking> bookings = bookingRepo.getByUserId(userId);
        return bookings.stream()
                .map(BookingResponseDTO::new)
                .collect(Collectors.toList());
    }
	
	public ResponseEntity<String> deleteBookingById(int id) {
		Optional<Booking> existingBooking = bookingRepo.findById(id);
		
		if(!existingBooking.isPresent()) {
			return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
		}
		
		Booking booking = existingBooking.get();
		bookingRepo.delete(booking);
		
		return new ResponseEntity<>("Delete booking with id: " + id, HttpStatus.OK);
	}

}
