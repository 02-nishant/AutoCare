package com.shop.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.entities.Vehicle;
import com.shop.service.vehicleService;

@RestController
@RequestMapping("/api/vehicle")
public class vehicleController {


	
	@Autowired
	private vehicleService vehicleService;
	
	public vehicleController(vehicleService vehicleService) {
		super();
		this.vehicleService = vehicleService;
	}
	
	
	@PostMapping("/")
	public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
		return new ResponseEntity<Vehicle>(vehicleService.createVehicle(vehicle), HttpStatus.CREATED);

	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/")
	public List<Vehicle> getAllVehicle() {
		return vehicleService.getAllVehicle();
	}
	
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") int id) {
		return new ResponseEntity<Vehicle>(vehicleService.getVehicleById(id), HttpStatus.OK);

	}
	
//	@GetMapping("/pendingFines/{fines}")
//	public List<Vehicle> getVehicleByPendingFines(@PathVariable("fines") int fines) {
//		return vehicleService.getVehicleByPendingFines(fines);
//	}
//	
	
	@GetMapping("/category/{category}")
	public Optional<Vehicle> getVehicleByCategory(@PathVariable("category") String category) {
		return vehicleService.getVehicleByCategory(category);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Vehicle> updateVehicle( @RequestBody Vehicle vehicle,@PathVariable("id") int id) {
		return new ResponseEntity<Vehicle>(vehicleService.updateVehicle(vehicle, id), HttpStatus.OK);

	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteVehicle(@PathVariable("id") int id) {
		vehicleService.deleteVehicle(id);
		return new ResponseEntity<String>("Vehicle record deleted", HttpStatus.OK);

	}
	
}
