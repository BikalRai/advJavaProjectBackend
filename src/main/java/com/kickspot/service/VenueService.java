package com.kickspot.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kickspot.dto.VenueRequestDTO;
import com.kickspot.model.Venue;
import com.kickspot.repository.UserRepository;
import com.kickspot.repository.VenueRepository;

@Service
public class VenueService {
	
	@Autowired
	private VenueRepository venueRepo;	

	@Autowired
	private UserRepository userRepo;
	
//	@Autowired
//	private TimeSlotService timeSlotService;
	
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
	
	public Venue UpdateVenue(int id, VenueRequestDTO venueReqDTO) {
		Optional<Venue> existingVenue = venueRepo.findById(id);
		
		if(!existingVenue.isPresent()) {
			return new Venue();
		}
		
		Venue venue = existingVenue.get();
		
		venue.setName(venueReqDTO.getName());
		venue.setLocation(venueReqDTO.getLocation());
		venue.setDescription(venueReqDTO.getDescription());
		venue.setAmenities(venueReqDTO.getAmenities());
		venue.setPrice(venueReqDTO.getPrice());
		venue.setImage(venueReqDTO.getImage());
		venue.setOpeningTime(venueReqDTO.getOpeningTime());
		venue.setClosingTime(venueReqDTO.getClosingTime());
		venue.setSlotDurationMinutes(60);
		
		return venueRepo.save(venue);
	
	}
	
	public String deleteVenueById(int id) {
		Optional<Venue> existingVenue = venueRepo.findById(id);
		
		if(!existingVenue.isPresent()) {
			return "Venue not found";
		}
		Venue venue = existingVenue.get();
		
		System.out.println(venue);
		
//		Optional<User> existingVenueOwner = userRepo.findById(venue.getOwner().getId());
//		if(!existingVenueOwner.isPresent()) {
//			return "Venue owner not found";
//		}
//		
//		User venueOwner = existingVenueOwner.get();
//		System.out.println(venue.getOwner().getId());
//		if(!(venue.getOwner().getId() == ownerId)) {
//			return "You do not have persmission to delete the venue";
//		}
		
		venueRepo.deleteById(id);
		return "Deleted venue with id: " + id;
	}
	
	public List<Venue> getAllVenues() {
		return venueRepo.findAll();
	}
}
