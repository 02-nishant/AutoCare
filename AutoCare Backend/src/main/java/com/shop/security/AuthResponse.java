package com.shop.security;

import java.util.List;

import com.shop.entities.User;
import com.shop.entities.Vehicle;

public class AuthResponse {
    private String token;
    private Integer id;
    private String name;
    private String email;
    private String address;
    private String role;
    private List<Vehicle> vehicle;
    
    

	public List<Vehicle> getVehicle() {
		return vehicle;
	}

	public void setVehicle(List<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}

	public String getToken() {
        return token;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    

    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public AuthResponse(String token, User user) {
        this.token = token;
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.role = user.getRoles();
        this.vehicle = user.getVehicle();
    }
}
