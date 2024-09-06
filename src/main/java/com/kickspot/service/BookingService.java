package com.kickspot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kickspot.model.booking.Booking;
import com.kickspot.repository.BookingRepository;

@Service
public class BookingService {
	
	@Autowired
	private BookingRepository bookingRepo;
	
	public Booking createBooking(Booking booking){
		Booking newBooking = new Booking();
		
		newBooking.setStartTime(booking.getStartTime());
		newBooking.setEndTime(booking.getEndTime());
		newBooking.setBookingDate(booking.getBookingDate());
		newBooking.setPrice(booking.getPrice());
		newBooking.setStatus("pending");
		
		return bookingRepo.save(newBooking);
		
	}
	
}
