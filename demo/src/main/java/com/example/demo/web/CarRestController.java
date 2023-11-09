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

	
	@Autowired
	private CarRepository carRepository;
	
    @RequestMapping(value="/cars", method = RequestMethod.GET)
    public @ResponseBody List<Car> carlistrest() {	
        return (List<Car>) carRepository.findAll();
    } 
    
    @RequestMapping(value="/cars/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Car> findbookrest(@PathVariable("id") Long carId) {	
        return carRepository.findById(carId);
    }  
}
