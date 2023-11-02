package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.example.demo.domain.Car;
import com.example.demo.domain.CarRepository;

@Controller
public class CarController {
	
    @Autowired
    private CarRepository carRepository;
	
	@GetMapping("/carmuseum")
    public String index(Model model) {
		List<Car> cars = (List<Car>) carRepository.findAll();
        model.addAttribute("cars", cars);
        return "carmuseum"; 
    }
	
	@GetMapping ("/add")
	public String addCarForm(Model model) {
        model.addAttribute("car", new Car());
        
        return "addcar";
    }
	
    @PostMapping("/save")
    public String addCarSubmit(@ModelAttribute Car car) {
        carRepository.save(car);
        return "redirect:/carmuseum";
    }
    

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
        return "redirect:/carmuseum";
    }
}
