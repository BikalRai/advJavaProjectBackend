package com.kickspot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kickspot.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
