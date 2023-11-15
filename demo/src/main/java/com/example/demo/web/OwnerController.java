package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.Owner;
import com.example.demo.domain.OwnerRepository;

@Controller
public class OwnerController {

    // Autowired OwnerRepository for accessing owner data
    @Autowired
    private OwnerRepository ownerRepository;

    // Handles GET requests for displaying a list of owners
    @GetMapping("/ownerlist")
    public String ownerList(Model model) {
       
    	// Retrieves all owners from the repository
        Iterable<Owner> owners = ownerRepository.findAll();
        
        // Adds the list of owners to the model for rendering in the view
        model.addAttribute("owners", owners);
        
        // Returns the name of the view template (in this case, "ownerlist")
        return "ownerlist";
    }

    // Handles GET requests for displaying the form to add a new owner
    @GetMapping("addowner")
    public String addowner(Model model) {
        
    	// Retrieves all owners from the repository
        Iterable<Owner> owners = ownerRepository.findAll();
        
        // Adds the list of owners to the model for rendering in the view
        model.addAttribute("owners", owners);
        
        // Adds a new Owner object to the model
        model.addAttribute("owner", new Owner());
        
        // Returns the name of the view template (in this case, "addowner")
        return "addowner";
    }

    // Handles POST requests for saving a new owner
    @PostMapping("/saveowner")
    public String saveOwner(@ModelAttribute Owner owner, Model model) {
        
    	// Saves the new owner to the repository
        ownerRepository.save(owner);
        
        // Retrieves all owners from the repository
        Iterable<Owner> owners = ownerRepository.findAll();
        
        // Adds the list of owners to the model for rendering in the view
        model.addAttribute("owners", owners);
        
        // Redirects to the "/ownerlist" endpoint after saving
        return "redirect:/ownerlist";
    }

    // Handles GET requests for deleting an owner by ID
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/deleteowner/{id}")
    public String deleteOwner(@PathVariable Long id) {
        
    	// Deletes the owner with the specified ID from the repository
        ownerRepository.deleteById(id);
        
        // Redirects to the "/ownerlist" endpoint after deletion
        return "redirect:/ownerlist";
    }
}
