package com.kickspot.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kickspot.model.TimeSlot;
import com.kickspot.model.Venue;
import com.kickspot.repository.TimeSlotRepository;

@Service
public class TimeSlotService {
	
	@Autowired
	private TimeSlotRepository timeSlotRepo;
	
	@Autowired
	private TimeSlotGeneratorService timeSlotGenerator;
	
	public List<TimeSlot> getAvailableTimeSlotForDate(LocalDate date) {
		return timeSlotRepo.findByDateAndAvailableTrue(date);
	}

	
	public List<TimeSlot> generateAndGetTimeSlotForDate(Venue venue, LocalDate date) {
	    // Check if time slots already exist for the date
	    List<TimeSlot> existingSlots = timeSlotRepo.findByVenueIdAndDate(venue, date);
	    if (!existingSlots.isEmpty()) {
	        return existingSlots; // Return existing slots if they exist
	    }

	    // Generate new time slots if none exist for the selected date
	    List<TimeSlot> generatedSlots = generateTimeSlotsForVenue(venue, date);
	    timeSlotRepo.saveAll(generatedSlots);

	    return generatedSlots;
	}

	private List<TimeSlot> generateTimeSlotsForVenue(Venue venue, LocalDate date) {
	    List<TimeSlot> slots = new ArrayList<>();
	    LocalTime currentTime = venue.getOpeningTime();
	    LocalTime closingTime = venue.getClosingTime();

	    while (currentTime.isBefore(closingTime)) {
	        LocalTime endTime = currentTime.plusHours(1);
	        if (endTime.isAfter(closingTime)) {
	            endTime = closingTime;
	        }

	        TimeSlot slot = new TimeSlot();
	        slot.setVenueId(venue);;
	        slot.setDate(date);
	        slot.setStartTime(currentTime);
	        slot.setEndTime(endTime);
	        slot.setAvailable(true);
	        // Set other properties as needed, e.g., price

	        slots.add(slot);
	        currentTime = endTime;
	    }

	    return slots;
	}
//	
}
