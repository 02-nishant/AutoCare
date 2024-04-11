package com.shop.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class User implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank(message= "Name can't be Empty")
	private String name;
	
	@NotBlank(message= "Email can't be Empty")
	@Email
	@Column(unique = true)
	private String email;
	
	@NotBlank(message="Contact number can't be Empty")
	private String number;
	
	@NotBlank(message= "password can't be empty")
	private String password;
	
	private  String roles="ROLE_USER";
	
	@NotBlank(message="Address can't be Empty")
	private String address;
	
	
	
	
	public  String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", number=" + number + ", password="
				+ password + ", address=" + address + ", vehicle=" + vehicle + "]";
	}
	public User(int id, String name, String email, String number, String password, String address) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.number = number;
		this.password = password;
		this.address = address;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	 @JsonManagedReference
	private List<Vehicle> vehicle;
	public List<Vehicle> getVehicle() {
		return vehicle;
	}
	
	public void setVehicle(List<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}
	
	public User(List<com.shop.entities.Vehicle> vehicle) {
		super();
		this.vehicle = vehicle;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		    
		    // Check if the user has the "ROLE_USER" role
		    if (this.roles.equals("ROLE_USER")) {
		        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		    }
		    
		    // Check if the user has the "ROLE_ADMIN" role
		    if (this.roles.equals("ROLE_ADMIN")) {
		        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		    }
		    
		    return authorities;
	}
	
	
	@Override
	public String getUsername() {
		
		return this.email;
	}
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}
	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
	
	
}
