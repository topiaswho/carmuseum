package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.domain.Car;
import com.example.demo.domain.CarRepository;

import com.example.demo.domain.Location;
import com.example.demo.domain.LocationRepository;

@SpringBootApplication
public class CarmuseumApplication {

	public static void main(String[] args) {
		final Logger log = LoggerFactory.getLogger(CarmuseumApplication.class);
		SpringApplication.run(CarmuseumApplication.class, args);
	}
	
	 @Bean
	    public CommandLineRunner locationdemo(LocationRepository LocationRepo) {
	        return (args) -> {
	            Location l1 = new Location("Kuopio");
	            Location l2 = new Location("Helsinki");

	            LocationRepo.save(l1);
	            LocationRepo.save(l2);
	        };
	 }
	@Bean
	public CommandLineRunner cardemo(
			CarRepository CarRepo,
			LocationRepository LocationRepo ) {
		return (args) -> {
			
			Location l1 = LocationRepo.findByLocationName("Kuopio");
			Location l2 = LocationRepo.findByLocationName("Helsinki");
			
			LocationRepo.save(l1);
			LocationRepo.save(l2);
			
			Car c1 = new Car("Mercedes-benz", "230SL", 1967, l1);
			Car c2 = new Car("BMW", "CSL", 1972, l2);
			
			CarRepo.save(c1);
			CarRepo.save(c2);
		};
	}
}
