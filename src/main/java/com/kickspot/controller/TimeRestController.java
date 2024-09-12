package com.kickspot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kickspot.model.TimeSlot;
import com.kickspot.model.Venue;
import com.kickspot.service.TimeSlotService;
import com.kickspot.service.VenueService;

@RestController
@RequestMapping("/api/time")
public class TimeRestController {
	
	@Autowired
	private TimeSlotService timeSlotService;
	
	@Autowired
	private VenueService venueService;
	
	@GetMapping("/{venueId}")
	public ResponseEntity<List<TimeSlot>> getTimeSlotForVenue(@PathVariable("venueId") int id) {
		Venue existingVenue = venueService.getById(id);
		
		if (existingVenue == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(timeSlotService.getTimeSlotForVenue(existingVenue));
	}
	
}
