package com.kickspot.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kickspot.model.TimeSlot;
import com.kickspot.model.Venue;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Integer> {

	List<TimeSlot> findByDateAndAvailableTrue(LocalDate date);

	List<TimeSlot> findByVenueIdAndDate(Venue venue, LocalDate date);
	
	List<TimeSlot> findByVenueId(Venue venueId);

}
