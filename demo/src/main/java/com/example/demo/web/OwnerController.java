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

	 @Autowired
	 private OwnerRepository ownerRepository;
	 
	 @GetMapping("/ownerlist")
	    public String ownerList(Model model) {
	        Iterable<Owner> owners = ownerRepository.findAll();
	        model.addAttribute("owners", owners);
	        return "ownerlist";
	    }
	 @GetMapping ("addowner")
	    public String addowner (Model model) {
	    	Iterable<Owner> owners = ownerRepository.findAll();
	        model.addAttribute("owners", owners);
	        model.addAttribute("owner", new Owner());
	    	return "addowner";
	    }
	 
	    @PostMapping("/saveowner")
	    public String saveOwner(@ModelAttribute Owner owner, Model model) {
	    	ownerRepository.save(owner);
	        Iterable<Owner> owners = ownerRepository.findAll();
	        model.addAttribute("owners", owners);
	        return "redirect:/ownerlist";
	    }
	    
	    @PreAuthorize("hasAuthority('ADMIN')")
	    @GetMapping("/deleteowner/{id}")
	    public String deleteOwner(@PathVariable Long id) {
	    	ownerRepository.deleteById(id);
	        return "redirect:/ownerlist";
	    }
}
