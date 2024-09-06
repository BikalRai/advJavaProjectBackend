package com.kickspot.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kickspot.model.Venue;
import com.kickspot.repository.VenueRepository;

@Service
public class VenueService {
	
	@Autowired
	private VenueRepository venueRepo;
	
	@Autowired
	private TimeSlotService timeSlotService;
	
	public Venue createVenue(Venue venue) {
		
		if(venue.getOpeningTime() == null) {
			venue.setOpeningTime(LocalTime.of(6, 0));
		}
		
		if(venue.getClosingTime() == null) {
			venue.setClosingTime(LocalTime.of(6, 0));
		}
		
		if(venue.getSlotDurationMinutes() == 0) {
			venue.setSlotDurationMinutes(60);
		}
		
		Venue savedVenue = venueRepo.save(venue);
//		
//		LocalDate startDate = LocalDate.now();
//		LocalDate endDate = startDate.plusDays(30);
//		timeSlotService.generateAndSaveTimeSlotForVenue(savedVenue, startDate, endDate);
		
		return savedVenue;
	}
	
	public Venue findById(int id) {
		Optional<Venue> existingVenue = venueRepo.findById(id);
		
		if(!existingVenue.isPresent()) {
			return new Venue();
		}
		
		Venue venue = existingVenue.get();
		
		return venue;
	}
}
