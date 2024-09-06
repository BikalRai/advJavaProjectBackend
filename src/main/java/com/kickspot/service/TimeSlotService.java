package com.kickspot.service;

import java.time.LocalDate;
import java.time.LocalTime;
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

	public void generateAndSaveTimeSlotForVenue(Venue venue, LocalDate startDate, LocalDate endDate) {
		
		
		for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
			List<TimeSlot> generatedSlots = timeSlotGenerator.generateTimeSlotsForVenue(venue, date);
			timeSlotRepo.saveAll(generatedSlots);
		}
	}
	
	// New method to generate and fetch time slots for a specific date
    public List<TimeSlot> generateAndGetTimeSlotForDate(Venue venue, LocalDate date) {
        // Check if time slots already exist for the date
        List<TimeSlot> existingSlots = timeSlotRepo.findByVenueIdAndDate(venue, date);
        if (!existingSlots.isEmpty()) {
            return existingSlots; // Return existing slots if they exist
        }

        // Generate new time slots if none exist for the selected date
        List<TimeSlot> generatedSlots = timeSlotGenerator.generateTimeSlotsForVenue(venue, date);
        timeSlotRepo.saveAll(generatedSlots);

        return generatedSlots;
    }
	
}
