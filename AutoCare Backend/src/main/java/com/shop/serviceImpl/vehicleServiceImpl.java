package com.shop.serviceImpl;

import java.util.List;
import java.util.Optional;
import com.shop.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shop.repository.vehicleRepo;
import com.shop.entities.Vehicle;
import com.shop.service.vehicleService;

@Service
public class vehicleServiceImpl implements vehicleService {
	
	@Autowired
	private vehicleRepo vehicleRepo;
	
	public vehicleServiceImpl(vehicleRepo vehicleRepo){
		super();
		this.vehicleRepo = vehicleRepo;
	}
	
	@Override
	public Vehicle createVehicle(Vehicle vehicle) {
		
		return vehicleRepo.save(vehicle);
	}

	@Override
	public List<Vehicle> getAllVehicle() {
		
		return vehicleRepo.findAll();
	}

	@Override
	public Vehicle getVehicleById(int id) {
		Optional<Vehicle> vehicle = vehicleRepo.findById(id);
		if(vehicle.isPresent()) {
			return vehicle.get();
		}else {
			throw new ResourceNotFound("Vehicle", "id", id);
		}
		
	}

//	@Override
//	public List<Vehicle> getVehicleByPendingFines(int fines) {
//		
//		return vehicleRepo.findByPendingFines(fines);
//	}

	@Override
	public Vehicle updateVehicle(Vehicle vehicle, int id) {
		Vehicle newVehicle = new Vehicle();
		try {
			newVehicle = vehicleRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Vehicle", "id", id));
		} catch (ResourceNotFound e) {
			e.printStackTrace();
		}
		
		newVehicle.setCategory(vehicle.getCategory());
		newVehicle.setPlateNumber(vehicle.getPlateNumber());
		newVehicle.setCompany(vehicle.getCompany());
		newVehicle.setRegistrationDate(vehicle.getRegistrationDate());
	
		
		vehicleRepo.save(newVehicle);
		return newVehicle;
	}

	@Override
	public void deleteVehicle(int id) {
		vehicleRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Vehicle", "id", id));
		vehicleRepo.deleteById(id);


	}

	@Override
	public Optional<Vehicle> getVehicleByCategory(String category) {
		return vehicleRepo.findByCategory(category);

	}

	@Override
	public List<Vehicle> getVehiclesByUserId(int id) {
		return vehicleRepo.findVehiclesByUserId(id);

	}

}
