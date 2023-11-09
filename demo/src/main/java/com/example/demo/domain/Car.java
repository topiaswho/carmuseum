package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    @Column(name = "release_year")
    private int year;
    @JsonIgnoreProperties("cars")
    @ManyToOne
    @JoinColumn(name = "locationid")
    private Location location;
    
    public Car() {
        this.brand = null;
        this.model = null;
        this.year = 0;
        this.location = null;

    }
    
    public Car(String brand, String model, int year, Location location) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.location = location;

    }

	public Long getId() {
		return id;
	}
  
	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Location getLocation() {
        return location;
    }
	
	public void setLocation(Location location) {
        this.location = location;
    }
	
	@Override
	public String toString() {
		if (this.location != null) {
		return "Car [id=" + id + ", brand=" + brand + ", model=" + model + ", year=" + this.getLocation() + "]";
	} else {
		return "Car [id=" + id + ", brand=" + brand + ", model=" + model + ", year=" + "]";
	}



	} 
}
