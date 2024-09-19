package com.kickspot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kickspot.model.booking.Booking;
import com.kickspot.model.booking.BookingRequestDTO;
import com.kickspot.model.booking.BookingResponseDTO;
import com.kickspot.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingRestController {
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createBooking(@RequestBody BookingRequestDTO bookingReqDto) {
		return bookingService.createBooking(bookingReqDto);
	}
	
	@GetMapping
	public ResponseEntity<List<Booking>> getAllBookings() {
		return new ResponseEntity<>(bookingService.showAllBookings(), HttpStatus.OK);
	}
	
	@GetMapping("/{userId}/bookings")
	public List<BookingResponseDTO> allUserBookings(@PathVariable("userId") int id) {
		return bookingService.showUserBookings(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletebooking(@PathVariable("id") int id) {
		return bookingService.deleteBookingById(id);
	}
}
