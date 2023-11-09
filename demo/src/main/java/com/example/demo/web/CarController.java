package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.Car;
import com.example.demo.domain.CarRepository;
import com.example.demo.domain.Location;
import com.example.demo.domain.LocationRepository;

@Controller
public class CarController {
	
    @Autowired
    private CarRepository carRepository;
    
    @Autowired
    private LocationRepository locationRepository;
	
	@GetMapping("/carmuseum")
    public String index(Model model) {
		List<Car> cars = (List<Car>) carRepository.findAll();
        model.addAttribute("cars", cars);
        return "carmuseum"; 
    }
	
	@GetMapping ("/add")
	public String addCarForm(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("locations", locationRepository.findAll());
        return "addcar";
    }
	

	
    @PostMapping("/save")
    public String addCarSubmit(@ModelAttribute Car car) {
    	System.out.println("Car" + car);
        carRepository.save(car);
        return "redirect:/carmuseum";
    }
    

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
        return "redirect:/carmuseum";
    }
    
    @GetMapping("/edit/{id}")
    public String editCar(@PathVariable Long id, Model model) {
        Car car = carRepository.findById(id).orElse(null);
        if (car != null) {
            model.addAttribute("car", car);
            return "editcar";
        } else {
            return "redirect:/carmuseum"; // Redirects to the carmuseum page if the car is not found
        }
    }
    @PostMapping("/updatecar")
    public String updateCar(@ModelAttribute Car car) {
        carRepository.save(car);
        return "redirect:/carmuseum"; // Redirect to the car museum page
    }

}
