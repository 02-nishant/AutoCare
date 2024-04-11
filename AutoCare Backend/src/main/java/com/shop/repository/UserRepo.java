package com.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entities.User;

public interface UserRepo extends JpaRepository<User,Integer>{
	User  findByEmail(String email);
	

}
