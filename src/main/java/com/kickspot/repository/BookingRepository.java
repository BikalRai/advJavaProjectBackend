package com.kickspot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kickspot.model.booking.Booking;
import com.kickspot.model.booking.BookingResponseDTO;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

	@Query(value = "SELECT * FROM BOOKING WHERE user_id = ?", nativeQuery = true)
	List<BookingResponseDTO> findByUserId(int id);
}
