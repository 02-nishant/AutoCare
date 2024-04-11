package com.shop.repository;
//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entities.*;


public interface insuranceRepo extends JpaRepository<Insurance, Integer>{  
	
//	List<Insurance> findByProvider(String insuranceProvider);
	

}
