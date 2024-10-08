package com.kickspot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kickspot.dto.UserResponseDTO;
import com.kickspot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	boolean existsByEmail(String email);
	boolean existsByMobile(String mobile);
	
	@Query(value = "SELECT * FROM USERS", nativeQuery = true)
	List<UserResponseDTO> findAllUsers();
	
	@Query(value = "SELECT * FROM users where id = ?1", nativeQuery = true)
	UserResponseDTO getUserById(int id);
	
	@Query(value = "SELECT * FROM USERS WHERE mobile = ?1", nativeQuery = true)
	UserResponseDTO getByMobile(String mobile);
	
	Optional<User> findByEmail(String email);
	Optional<User> findByMobile(String mobile);
	
	
}
