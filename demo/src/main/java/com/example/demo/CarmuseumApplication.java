package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.domain.Car;
import com.example.demo.domain.CarRepository;

@SpringBootApplication
public class CarmuseumApplication {

	public static void main(String[] args) {
		final Logger log = LoggerFactory.getLogger(CarmuseumApplication.class);
		SpringApplication.run(CarmuseumApplication.class, args);
	}
	@Bean
	public CommandLineRunner cardemo(
			CarRepository CarRepo) {
		return (args) -> {
			Car c1 = new Car("Mercedes-benz", "230SL", 1967);
			Car c2 = new Car("BMW", "CSL", 1972);
			
			CarRepo.save(c1);
			CarRepo.save(c2);
		};
	}
}
