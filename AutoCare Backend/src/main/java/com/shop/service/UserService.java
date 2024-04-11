package com.shop.service;

import java.util.List;

import com.shop.entities.User;

public interface UserService {
	User registerNewUser(User user);
	
	User createUser(User user);
	User updateUser(User user, Integer userId);
	User getUserById(Integer userId);
	List<User> getAllUser();
	void deleteUser(Integer userId);
	
	void updatePassword(Integer id, String oldPass, String newPass);

	User findUserByEmail(String email);
}
