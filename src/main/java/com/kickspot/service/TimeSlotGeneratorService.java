package com.kickspot.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kickspot.model.TimeSlot;
import com.kickspot.model.Venue;

@Service
public class TimeSlotGeneratorService {

	public List<TimeSlot> generateTimeSlotsForVenue(Venue venue, LocalDate date) {

		List<TimeSlot> timeSlots = new ArrayList<>();
		LocalTime currentTime = venue.getOpeningTime();

		while (currentTime.plusMinutes(venue.getSlotDurationMinutes()).isBefore(venue.getClosingTime())
				|| currentTime.plusMinutes(venue.getSlotDurationMinutes()).equals(venue.getClosingTime())) {
			TimeSlot slot = new TimeSlot();
			slot.setVenueId(venue);
			slot.setDate(date);
			slot.setStartTime(currentTime);
			slot.setEndTime(currentTime.plusMinutes(venue.getSlotDurationMinutes()));
			slot.setAvailable(true);

			timeSlots.add(slot);
			currentTime = currentTime.plusMinutes(venue.getSlotDurationMinutes());

		}

		return timeSlots;
	}
}
