package com.kickspot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kickspot.model.booking.BookingRequestDTO;
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
}
