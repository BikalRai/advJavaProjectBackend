package com.kickspot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kickspot.model.Match;

public interface MatchRepository extends JpaRepository<Match, Integer> {

}
