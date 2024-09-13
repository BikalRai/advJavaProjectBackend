package com.kickspot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kickspot.model.booking.Booking;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

//	@Query(value = "SELECT * FROM BOOKING WHERE user_id = ?1", nativeQuery = true)
	@Query("SELECT b FROM Booking b WHERE b.user.id = :id")	
	List<Booking> getByUserId(@Param("id") int id);

}
