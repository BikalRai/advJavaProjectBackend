package com.kickspot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kickspot.model.AppServices;

public interface AppServiceRepository extends JpaRepository<AppServices, Integer> {

}
