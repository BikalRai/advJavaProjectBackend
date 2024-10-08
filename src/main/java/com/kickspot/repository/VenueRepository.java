package com.kickspot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kickspot.model.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Integer> {

}
