package com.shop.controllers;

import java.util.List;

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

import com.shop.entities.Insurance;
import com.shop.service.*;

@RestController
@RequestMapping("/api/insurance")
public class insuranceController {
	
	
	@Autowired
	private insuranceService insuranceService;
	
	public insuranceController(insuranceService insuranceService) {
		super();
		this.insuranceService = insuranceService;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/")
	public ResponseEntity<Insurance> createInsurance(@RequestBody Insurance insurance) {
		return new ResponseEntity<Insurance>(insuranceService.createInsurance(insurance), HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public List<Insurance> getAllInsurance() {
		return insuranceService.getAllInsurance();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Insurance> getInsuranceById(@PathVariable("id") int id) {
		return new ResponseEntity<Insurance>(insuranceService.getInsuranceById(id), HttpStatus.OK);

	}
	
//	@GetMapping("/insuranceByProvider/{provider}")
//	public List<Insurance> getInsuranceByProvider(
//			@PathVariable("provider") String provider) {
//		return insuranceService.getInsuranceByProvider(provider);
//	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Insurance> updateInsurance( @RequestBody Insurance insurance, @PathVariable("id")int id) {
		return new ResponseEntity<Insurance>(insuranceService.updateInsurance(insurance, id), HttpStatus.OK);

	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteInsurance(@PathVariable("id") int id) {
		insuranceService.deleteInsurance(id);
		return new ResponseEntity<String>("Insurance record deleted", HttpStatus.OK);

	}

}
