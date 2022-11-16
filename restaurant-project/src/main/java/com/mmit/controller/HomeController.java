package com.mmit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.mmit.model.service.MenuService;
import com.mmit.model.service.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private MenuService menuService;
	
	@GetMapping("/")
	public String home() {
		return "redirect:/restaurent";
	}
	
	@GetMapping("/restaurent")
	public String goHome(ModelMap map) {
		map.put("productList", productService.findAll());
		map.put("menuList", menuService.findAll());
		return "home";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	@GetMapping("/team")
	public String team() {
		return "team";
	}
}
