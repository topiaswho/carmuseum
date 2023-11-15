package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.Location;
import com.example.demo.domain.LocationRepository;

@Controller
public class LocationController {

    // Autowired LocationRepository for accessing location data
    @Autowired
    private LocationRepository locationRepository;

    // Handles GET requests for displaying a list of locations
    @GetMapping("/locationlist")
    public String locationList(Model model) {
        
    	// Retrieves all locations from the repository
        Iterable<Location> locations = locationRepository.findAll();
        
        // Adds the list of locations to the model for rendering in the view
        model.addAttribute("locations", locations);
        
        // Returns the name of the view template (in this case, "locationlist")
        return "locationlist";
    }

    // Handles GET requests for displaying the form to add a new location
    @GetMapping("addlocation")
    public String addlocation(Model model) {
        
    	// Retrieves all locations from the repository
        Iterable<Location> locations = locationRepository.findAll();
        
        // Adds the list of locations to the model for rendering in the view
        model.addAttribute("locations", locations);
        
        // Returns the name of the view template (in this case, "addlocation")
        return "addlocation";
    }

    // Handles POST requests for saving a new location
    @PostMapping("/savelocation")
    public String saveLocation(@ModelAttribute Location location, Model model) {
        
    	// Saves the new location to the repository
        locationRepository.save(location);
        
        // Retrieves all locations from the repository
        Iterable<Location> locations = locationRepository.findAll();
        
        // Adds the list of locations to the model for rendering in the view
        model.addAttribute("locations", locations);
        
        // Redirects to the "/locationlist" endpoint after saving
        return "redirect:/locationlist";
    }

    // Handles GET requests for deleting a location by ID
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/deletelocation/{id}")
    public String deleteLocation(@PathVariable Long id) {
        
    	// Deletes the location with the specified ID from the repository
        locationRepository.deleteById(id);
        
        // Redirects to the "/locationlist" endpoint after deletion
        return "redirect:/locationlist";
    }
}
