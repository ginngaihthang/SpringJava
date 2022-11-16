package com.mmit.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/admin")
	public String home() {
		
		return "admin/index";
	}
}
