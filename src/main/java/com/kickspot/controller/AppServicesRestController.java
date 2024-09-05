package com.kickspot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kickspot.model.AppServices;
import com.kickspot.service.AppServicesService;

@RestController
@RequestMapping("/api/services")
public class AppServicesRestController {
	
	@Autowired
	private AppServicesService appService;
	
	@PostMapping("/add")
	public AppServices addService(@RequestBody AppServices appServices) {
		
		return appService.addService(appServices);
	}
	
	@GetMapping
	public List<AppServices> getAllServices() {
		return appService.getAllServices();
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<String> updateServices(@PathVariable("id") int id, @RequestBody AppServices appServices) {
		return appService.update(id, appServices);
	}

}
