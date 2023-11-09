package com.example.demo.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long locationid;
    public String locationName;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
    private List<Car> cars;
    
    public Location() {
    }
    
    public Location(String locationName) {
        this.locationName = locationName;
    }

    public Long getLocationId() {
        return locationid;
    }

    public void setLocationId(Long locationid) {
        this.locationid = locationid;
    }

    public String getlocationName() {
        return locationName;
    }

    public void setlocationName(String locationName) {
        this.locationName = locationName;
    }
    public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	@Override
	public String toString() {
		return "Location{" +
                "locationId=" + locationid +
                ", locationName='" + locationName + '\'' +
                '}';
    }
	}
	
	

