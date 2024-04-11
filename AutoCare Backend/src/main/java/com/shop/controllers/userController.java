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

import com.shop.entities.User;
import com.shop.service.UserService;

@RestController
@RequestMapping("/api/user")
public class userController {
	
	
	@Autowired
	private UserService userService;
	
	public userController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user){	
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/")
	public List<User> getAllUser() {
		return userService.getAllUser();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
		return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
		return new ResponseEntity<String>("User Record Is Deleted", HttpStatus.OK);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") Integer id) {
		User existingUser = userService.getUserById(id);
	    
	   
	    user.setPassword(existingUser.getPassword());
	    user.setEmail(existingUser.getEmail());
	    
	  
	    User updatedUser = userService.updateUser(user, id);
	    
	   
	    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	
	
	@PutMapping("/{id}/{oldPass}/{newPass}")
    public ResponseEntity<String> updatePassword(
            @PathVariable("id") Integer id,@PathVariable("oldPass") String oldPass,@PathVariable("newPass") String newPass) {
        try {
            userService.updatePassword(id, oldPass, newPass);
            return new ResponseEntity<>("Password updated successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
	
}
