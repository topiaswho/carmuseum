package com.example.demo.web;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/locationlist")
    public String locationList(Model model) {
        Iterable<Location> locations = locationRepository.findAll();
        model.addAttribute("locations", locations);
        return "locationlist";
    }
    

    
    @GetMapping ("addlocation")
    public String addlocation (Model model) {
    	Iterable<Location> locations = locationRepository.findAll();
        model.addAttribute("locations", locations);
    	return "addlocation";
    }
    
    
    @PostMapping("/savelocation")
    public String saveLocation(@ModelAttribute Location location, Model model) {
        locationRepository.save(location);
        Iterable<Location> locations = locationRepository.findAll();
        model.addAttribute("locations", locations);
        return "redirect:/locationlist";
    }

    
    @GetMapping("/deletelocation/{id}")
    public String deleteLocation(@PathVariable Long id) {
        locationRepository.deleteById(id);
        return "redirect:/locationlist";
    }
}

