package com.shop.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shop.entities.User;
import com.shop.exceptions.ResourceNotFound;
import com.shop.repository.UserRepo;
import com.shop.service.UserService;

@Service
public class userServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public userServiceImpl(UserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	public User createUser(User user) {
		
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
	
		return userRepo.save(user);
	}

	@Override
	public User updateUser(User user, Integer userId) {
		User newUser = new User();
		try {
			newUser = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "Id", userId));
		} catch (ResourceNotFound e) {
			e.printStackTrace();
		}
		
		newUser.setName(user.getName());
		newUser.setNumber(user.getNumber());
		newUser.setAddress(user.getAddress());
		newUser.setPassword(user.getPassword());
		
		userRepo.save(newUser);
		
		return newUser;
	}

	@Override
	public User getUserById(Integer userId) {
		Optional<User> user = userRepo.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}else {
			throw new ResourceNotFound("User","Id",userId);
		}
		
	}

	@Override
	public List<User> getAllUser() {
		
		return userRepo.findAll();
	}

	@Override
	public void deleteUser(Integer userId) {
		
		userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "Id", userId));
		userRepo.deleteById(userId);
	}
	

	@Override
    public void updatePassword(Integer id, String oldPass, String newPass) {
        User user = getUserById(id);

        if (user.getPassword().equals(oldPass)) {
            user.setPassword(newPass);
            userRepo.save(user);
        } else {
            throw new RuntimeException("Password does not match.");
        }
    }

	@Override
	public User registerNewUser(User user) {
		
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		
		
		return userRepo.save(user);
	}

	@Override
	public User findUserByEmail(String email) {
		User user = userRepo.findByEmail(email);
		
		return user;
	}

}
