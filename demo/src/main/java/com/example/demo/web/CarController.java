package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.example.demo.domain.Car;
import com.example.demo.domain.CarRepository;

@Controller
public class CarController {
	
    @Autowired
    private CarRepository carRepository;
	
	@GetMapping("carmuseum")
    public String index(Model model) {
		List<Car> cars = (List<Car>) carRepository.findAll();
        model.addAttribute("cars", cars);
        return "carmuseum"; 
    }
}
