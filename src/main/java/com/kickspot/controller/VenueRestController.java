package com.kickspot.controller;

import java.time.LocalDate;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.kickspot.dto.VenueRequestDTO;
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

	@GetMapping("/{venueId}")
	public Venue getVenueById(@PathVariable("venueId") int id) {
		return venueService.getById(id);
	}

	@PostMapping("/{venueId}/timeslots")
	public ResponseEntity<String> getTimeSlotsForDate(@PathVariable("venueId") int venueId) {
	    Venue venue = venueService.getById(venueId);
	    LocalDate date = LocalDate.now();

	    if (venue != null) {
	        timeSlotService.generateAndGetTimeSlotForDate(venue, date);
	        String message = "Generating slots for venue " + venueId + " on date " + date;
	        return ResponseEntity.ok(message); // 200 OK with the message
	    } else {
	        String errorMessage = "Venue not found";
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage); // 404 Not Found with the error message
	    }
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
