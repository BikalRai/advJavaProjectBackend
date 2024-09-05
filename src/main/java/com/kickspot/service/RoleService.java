package com.kickspot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kickspot.model.Role;
import com.kickspot.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepo;
	
	public Role getRoleByName(String name) {
		Optional<Role> role = roleRepo.findByName(name);
		
		return role.orElse(null);
	}
	
	public Role getRoleById(int id) {
		Optional<Role> role = roleRepo.findById(id);
		
		return role.orElse(null);
	}
	
	public List<Role> getAllRolesById(List<Integer> roleIds) {
		return roleRepo.findAllById(roleIds);
	}

}
