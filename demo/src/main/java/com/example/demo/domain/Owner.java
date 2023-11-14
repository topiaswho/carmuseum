package com.example.demo.domain;

import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Owner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long ownerid;
	
	
    public String ownerName;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Car> cars;
    
    public Owner() {
    }
    
    public Owner(String ownerName) {
        this.ownerName = ownerName;
    }

	public Long getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(Long ownerid) {
		this.ownerid = ownerid;
	}

	public String getownerName() {
		return ownerName;
	}

	public void setownerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
	@Override
	public String toString() {
		return "Owner{" +
                "ownerid=" + ownerid +
                ", ownerName='" + ownerName + '\'' +
                '}';
	}

}
