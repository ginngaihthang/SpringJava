package com.mmit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		
		return "redirect:/shop";
	}
	
	@GetMapping("/shop")
	public String index() {
		return "index";
	}
	
}
