package com.shop.entities;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message="Category can't be Empty")
	private String category;
	
	@NotBlank(message="Plate Number can't be Empty")
	@Column(unique= true)
	private String plateNumber;
	
	@NotBlank(message="company can't be Empty")
	private String company;
	
	@NotNull
	private LocalDate registrationDate;
	
//	@NotNull
//	private LocalDate nextPreDate;
//	
////	 previous premium date
//	@NotNull
//	private LocalDate previousPreDate;
//	
//	@NotNull
//	private int pendingFines;
	
	@ManyToOne
	@JsonBackReference
	public User user;
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

//	public LocalDate getNextPreDate() {
//		return nextPreDate;
//	}
//
//	public void setNextPreDate(LocalDate nextPreDate) {
//		this.nextPreDate = nextPreDate;
//	}
//
//	public LocalDate getPreviousPreDate() {
//		return previousPreDate;
//	}
//
//	public void setPreviousPreDate(LocalDate previousPreDate) {
//		this.previousPreDate = previousPreDate;
//	}

//	public int getPendingFines() {
//		return pendingFines;
//	}
//
//	public void setPendingFines(int pendingFines) {
//		this.pendingFines = pendingFines;
//	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", category=" + category + ", plateNumber=" + plateNumber + ", company=" + company
				+ ", registrationDate=" + registrationDate + ",  user=" + user +  "]";
	}

	public Vehicle(int id, String category, String plateNumber, String company, LocalDate registrationDate,
		 User user) {
		super();
		this.id = id;
		this.category = category;
		this.plateNumber = plateNumber;
		this.company = company;
		this.registrationDate = registrationDate;
		
	
		this.user = user;
	}

	public Vehicle() {
		super();
		// TODO Auto-generated constructor stub
	}


	
//    public void setUser(Optional<User> findById) {
//		
//	}
	

}
