package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarController {
	
	@GetMapping("carmuseum")
    public String index(Model model) {
        
        return "carmuseum"; 
    }
}
