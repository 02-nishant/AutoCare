package com.shop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entities.Vehicle;



public interface vehicleRepo extends JpaRepository<Vehicle,Integer> {
	
	
	Optional<Vehicle> findByCategory(String category);
	List<Vehicle> findVehiclesByUserId(int id);
	
}
