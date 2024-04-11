package com.shop.service;

import java.util.List;

import com.shop.entities.Insurance;

public interface insuranceService {
	
	Insurance createInsurance(Insurance insurance);
	
	List<Insurance> getAllInsurance();
	
	Insurance getInsuranceById(int id);
	
	Insurance updateInsurance(Insurance insurance, int id);

	void deleteInsurance(int id);
	
//	List<Insurance> getInsuranceByProvider(String insuranceProvider);

}
