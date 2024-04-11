package com.shop.service;

import java.util.List;
import java.util.Optional;

import com.shop.entities.Vehicle;

public interface vehicleService {
	
	Vehicle createVehicle(Vehicle vehicle);
	
	List<Vehicle> getAllVehicle();
	
	Vehicle getVehicleById(int id);
	
//	List<Vehicle> getVehicleByPendingFines(int fines);
//	
	Vehicle updateVehicle(Vehicle vehicle, int id);
	
	void deleteVehicle(int id);
	
	Optional<Vehicle> getVehicleByCategory(String category);
	
	List<Vehicle> getVehiclesByUserId(int id);
	
}
