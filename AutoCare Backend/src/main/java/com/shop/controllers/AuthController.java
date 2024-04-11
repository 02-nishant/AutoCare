package com.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.entities.User;
import com.shop.security.*;
import com.shop.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> createToken(@RequestBody AuthRequest request){
		
		this.authenticate(request.getEmail(), request.getPassword());
		
		UserDetails  user = this.userDetailsService.loadUserByUsername(request.getEmail());
		User u = userService.findUserByEmail(request.getEmail());
		
		String token = this.jwtTokenHelper.generateToken(user);
		
		AuthResponse response = new AuthResponse(token , u);
		
		return new ResponseEntity<AuthResponse>(response, HttpStatus.OK);
		
		
	}

	private void authenticate(String email, String password) {

			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
			
			try {
				this.authenticationManager.authenticate(token);
			}catch(DisabledException e) {
				e.printStackTrace();
			}
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user){
		
		User newUser = this.userService.registerNewUser(user);
		
		return new ResponseEntity<User>(newUser,HttpStatus.CREATED);
		
	}
}
