package com.kickspot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kickspot.model.AppServices;
import com.kickspot.repository.AppServiceRepository;

@Service
public class AppServicesService {
	
	@Autowired
	private AppServiceRepository appService;
	
	public AppServices addService(AppServices appServices) {
		return appService.save(appServices);
	}
	
	public List<AppServices> getAllServices() {
		return appService.findAll();
	}
	
	public ResponseEntity<String> update(int id, AppServices appServices) {
		Optional<AppServices> existingService = appService.findById(id);
		
		if(!existingService.isPresent()) {
			return new ResponseEntity<>("Service not found", HttpStatus.BAD_REQUEST);
		}
		
		AppServices service = existingService.get();
		
		service.setServiceName(appServices.getServiceName());
		service.setDescription(appServices.getDescription());
		
		if(appServices.getImage() != null) {
			service.setImage(appServices.getImage());
		}
		
		appService.save(service);
		
		return new ResponseEntity<>("Updated service with id: " + appServices.getId(), HttpStatus.OK);
		
	}
}
