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
import com.example.demo.domain.Location;
import com.example.demo.domain.LocationRepository;
import com.example.demo.domain.OwnerRepository;

@Controller
public class CarController {
    
    @Autowired
    private CarRepository carRepository;
    
    @Autowired
    private LocationRepository locationRepository;
    
    @Autowired
    private OwnerRepository ownerRepository;

    // Handles the GET request for the "/carmuseum" endpoint
    @GetMapping("/carmuseum")
    public String index(Model model) {
        
    	// Retrieves all cars from the repository
        List<Car> cars = (List<Car>) carRepository.findAll();
        
        // Adds the list of cars to the model for rendering in the view
        model.addAttribute("cars", cars);
        
        // Returns the name of the view template (in this case, "carmuseum")
        return "carmuseum";
    }

    // Handles the GET request for the "/add" endpoint to show the form for adding a new car
    @GetMapping("/add")
    public String addCarForm(Model model) {
        
    	// Adds a new Car object, and lists of locations and owners to the model
        model.addAttribute("car", new Car());
        model.addAttribute("locations", locationRepository.findAll());
        model.addAttribute("owners", ownerRepository.findAll());
        
        // Returns the name of the view template (in this case, "addcar")
        return "addcar";
    }

    // Handles the POST request for the "/save" endpoint to save a new car
    @PostMapping("/save")
    public String addCarSubmit(@ModelAttribute Car car) {
        // Saves the new car to the repository
        carRepository.save(car);
        // Redirects to the "/carmuseum" endpoint after saving
        return "redirect:/carmuseum";
    }

    // Handles the GET request for the "/delete/{id}" endpoint to delete a car by ID
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        // Deletes the car with the specified ID from the repository
        carRepository.deleteById(id);
        // Redirects to the "/carmuseum" endpoint after deletion
        return "redirect:/carmuseum";
    }

    // Handles the GET request for the "/edit/{id}" endpoint to show the form for editing a car
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{id}")
    public String editCar(@PathVariable Long id, Model model) {
        
    	// Retrieves the car with the specified ID from the repository
        Car car = carRepository.findById(id).orElse(null);
        
        // Adds lists of locations and owners to the model
        model.addAttribute("locations", locationRepository.findAll());
        model.addAttribute("owners", ownerRepository.findAll());
        if (car != null) {
            
        	// Adds the car to the model if it exists
            model.addAttribute("car", car);
            
            // Returns the name of the view template (in this case, "editcar")
            return "editcar";
        } else {
        
        	// Redirects to the "/carmuseum" endpoint if the car is not found
            return "redirect:/carmuseum";
        }
    }

    // Handles the POST request for the "/updatecar" endpoint to update an existing car
    @PostMapping("/updatecar")
    public String updateCar(@ModelAttribute Car car) {
        
    	// Saves the updated car to the repository
        carRepository.save(car);
        
        // Redirects to the "/carmuseum" endpoint after updating
        return "redirect:/carmuseum";
    }
}
