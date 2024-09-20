package com.kickspot.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kickspot.dto.PaymentDetailsResponseDTO;
import com.kickspot.model.booking.Booking;
import com.kickspot.service.BookingService;

@RestController
@RequestMapping("/api/payment")
public class PaymentRestController {
	
	@Autowired
	private BookingService bookingService;
	
	@GetMapping("/details/{bookingId}")
	public ResponseEntity<PaymentDetailsResponseDTO> getBookingPaymentDetails(@PathVariable("bookingId") int id) {
		String transactionId = UUID.randomUUID().toString();
		
		try {
			Booking booking = bookingService.getBookingById(id);
			
			PaymentDetailsResponseDTO paymentDTO = new PaymentDetailsResponseDTO();
			paymentDTO.setTransactionId(transactionId);
			paymentDTO.setVenueName(booking.getVenue().getName());
			paymentDTO.setLocation(booking.getVenue().getLocation());
			paymentDTO.setDate(booking.getBookingDate());
			paymentDTO.setStartTime(booking.getTimeSlot().getStartTime());
			paymentDTO.setEndTime(booking.getTimeSlot().getEndTime());
			paymentDTO.setPrice(booking.getPrice());
			
			return new ResponseEntity<>(paymentDTO, HttpStatus.OK);
					
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
	}
	
}
