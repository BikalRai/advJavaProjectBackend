package com.kickspot.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kickspot.model.TimeSlot;
import com.kickspot.model.Venue;
import com.kickspot.service.TimeSlotService;
import com.kickspot.service.VenueService;

@RestController
@RequestMapping("/api/venues")
public class VenueRestController {

	@Autowired
	private VenueService venueService;
	
	@Autowired
	private TimeSlotService timeSlotService;
	
	@PostMapping("/create")
	public Venue createNewVenue(@RequestBody Venue venue) {
		Venue newVenue = new Venue();
		
		newVenue.setName(venue.getName());
		newVenue.setLocation(venue.getLocation());
		newVenue.setDescription(venue.getDescription());
		newVenue.setAmenities(venue.getAmenities());
		newVenue.setPrice(venue.getPrice());
		newVenue.setImage(venue.getImage());
		newVenue.setOpeningTime(venue.getOpeningTime());
		newVenue.setClosingTime(venue.getClosingTime());
		newVenue.setSlotDurationMinutes(venue.getSlotDurationMinutes());
		
		
		return venueService.createVenue(newVenue);
		
	}
	
	 
    @PostMapping("/{venueId}/timeslots")
    public List<TimeSlot> getTimeSlotsForDate(@PathVariable int venueId) {
        Venue venue = venueService.findById(venueId); 
        LocalDate date = LocalDate.now();
        return timeSlotService.generateAndGetTimeSlotForDate(venue, date);
    }
}
