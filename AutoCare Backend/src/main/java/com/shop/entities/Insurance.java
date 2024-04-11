package com.shop.entities;

import java.io.IOException;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message="Insurance Provider can't be Empty")
    private String insuranceProvider;

    @NotBlank(message="Insurance number can't be Empty")
    private String insuranceNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInsuranceProvider() {
        return insuranceProvider;
    }

    public void setInsuranceProvider(String insuranceProvider) {
        this.insuranceProvider = insuranceProvider;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    @Override
    public String toString() {
        return "Insurance [id=" + id + ", insuranceProvider=" + insuranceProvider + ", insuranceNumber="
                + insuranceNumber +  "]";
    }
    
// // Constructor with JSON string argument
//    public Insurance(String jsonString) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            Insurance insurance = objectMapper.readValue(jsonString, Insurance.class);
//            this.id = insurance.getId();
//            this.insuranceProvider = insurance.getInsuranceProvider();
//            this.insuranceNumber = insurance.getInsuranceNumber();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    // Constructor to generate a random insurance number
    public Insurance() {
        super();
        // Generate a random insurance number
        this.insuranceNumber = generateRandomInsuranceNumber();
    }

    // Method to generate a random insurance number
    private String generateRandomInsuranceNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        // Generate a 10-digit random number
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
