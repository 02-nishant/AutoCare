package com.shop.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shop.exceptions.ResourceNotFound;
import com.shop.entities.Insurance;
import com.shop.repository.insuranceRepo;
import com.shop.service.insuranceService;

@Service
public class insuranceServiceImpl implements insuranceService {

	@Autowired
	private insuranceRepo insuranceRepo;
	
	public insuranceServiceImpl(insuranceRepo insuranceRepo) {
		super();
		this.insuranceRepo = insuranceRepo;
	}
	
	@Override
	public Insurance createInsurance(Insurance insurance) {
		
		return insuranceRepo.save(insurance);
	}

	@Override
	public List<Insurance> getAllInsurance() {
		
		return insuranceRepo.findAll();
	}

	@Override
	public Insurance getInsuranceById(int id) {
		Optional<Insurance> insurance = insuranceRepo.findById(id);
		if (insurance.isPresent()) {
			return insurance.get();
		} else {
			throw new ResourceNotFound("Insurance", "id", id);
		}
	}

	@Override
	public Insurance updateInsurance(Insurance insurance, int id) {
		Insurance newIns = new Insurance();
		try {
			newIns = insuranceRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Insurance", "id", id));
		} catch (ResourceNotFound e) {
			e.printStackTrace();
		}
		
		newIns.setInsuranceNumber(insurance.getInsuranceNumber());
		newIns.setInsuranceProvider(insurance.getInsuranceProvider());
		
		insuranceRepo.save(newIns);
		
		return newIns;
	}

	@Override
	public void deleteInsurance(int id) {
		insuranceRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Insurance", "id", id));
		insuranceRepo.deleteById(id);

	}

//	@Override
//	public List<Insurance> getInsuranceByProvider(String provider) {
//		return insuranceRepo.findByProvider(provider);
//	}

}
