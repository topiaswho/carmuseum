package com.example.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.Car;
import com.example.demo.domain.CarRepository;

@CrossOrigin
@Controller
public class CarRestController {

    // Autowired CarRepository for accessing car data
    @Autowired
    private CarRepository carRepository;

    // Handles GET requests for retrieving a list of all cars
    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public @ResponseBody List<Car> carlistrest() {
        
    	// Retrieves and returns a list of all cars from the repository
        return (List<Car>) carRepository.findAll();
    }

    // Handles GET requests for retrieving a specific car by ID
    @RequestMapping(value = "/cars/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Car> findbookrest(@PathVariable("id") Long carId) {
        
    	// Retrieves and returns a specific car by its ID from the repository
        return carRepository.findById(carId);
    }
}
