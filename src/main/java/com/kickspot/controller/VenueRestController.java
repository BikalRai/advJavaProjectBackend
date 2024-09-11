package com.kickspot.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kickspot.dto.VenueRequestDTO;
import com.kickspot.model.TimeSlot;
import com.kickspot.model.User;
import com.kickspot.model.Venue;
import com.kickspot.repository.UserRepository;
import com.kickspot.service.TimeSlotService;
import com.kickspot.service.VenueService;

@RestController
@RequestMapping("/api/venues")
public class VenueRestController {

	@Autowired
	private VenueService venueService;
	
	@Autowired
	private TimeSlotService timeSlotService;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@PostMapping("/create")
	public ResponseEntity<String> createNewVenue(@RequestBody VenueRequestDTO venueReqDTO) {
		Venue newVenue = new Venue();
		
		newVenue.setName(venueReqDTO.getName());
		newVenue.setLocation(venueReqDTO.getLocation());
		newVenue.setDescription(venueReqDTO.getDescription());
		newVenue.setAmenities(venueReqDTO.getAmenities());
		newVenue.setPrice(venueReqDTO.getPrice());
		newVenue.setImage(venueReqDTO.getImage());
		newVenue.setOpeningTime(venueReqDTO.getOpeningTime());
		newVenue.setClosingTime(venueReqDTO.getClosingTime());
		newVenue.setSlotDurationMinutes(venueReqDTO.getSlotDurationMinutes());
		
//		Optional<User> existingUser = userRepo.findById(venueReqDTO.getOwnerId());
//		
//		if(!existingUser.isPresent()) {
//			return new ResponseEntity<>("Owner/User not found", HttpStatus.NOT_FOUND);
//		}
//		
//		User existingUserDetails = existingUser.get();
//		newVenue.setOwner(existingUserDetails);		
		
		venueService.createVenue(newVenue);
		
		return new ResponseEntity<>("Venue successfully added", HttpStatus.OK);
		
	}
	
	@GetMapping
	public List<Venue> getAllVenues() {
		return venueService.getAllVenues();
	}
	
	 
    @PostMapping("/{venueId}/timeslots")
    public List<TimeSlot> getTimeSlotsForDate(@PathVariable("venueId") int venueId) {
        Venue venue = venueService.findById(venueId); 
        LocalDate date = LocalDate.now();
        
        System.out.println("Generating slots for venue " + venueId + " on date " + date);
        
        return timeSlotService.generateAndGetTimeSlotForDate(venue, date);
    }
    
    @PutMapping("/update/{venueId}")
    public Venue updateVenueDetails(@PathVariable("venueId") int id, @RequestBody VenueRequestDTO venueReqDTO) {
    	return venueService.UpdateVenue(id, venueReqDTO);
    }
    
    @DeleteMapping("/delete/{venueId}")
    public String deleteVenueById(@PathVariable("venueId") int id) {
    	return venueService.deleteVenueById(id);
    }
}
