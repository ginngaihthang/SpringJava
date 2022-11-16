package com.mmit.admin.controller;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mmit.model.entity.User;
import com.mmit.model.service.OrderItemService;
import com.mmit.model.service.OrderService;
import com.mmit.model.service.UserService;


@Controller
public class AdminHomeController {
	@Autowired
	private UserService userService;
	@Autowired
	private OrderItemService itemService;
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/admin")
	public String home(ModelMap map) {
		
		map.put("orderItemlist", itemService.findAll());
		return "admin/home";
	}
	

	

	@ModelAttribute
	public void assignDefaultModel(ModelMap map, Principal principal) {
		User loginuser = userService.profile(principal.getName());
		
		map.put("admin", loginuser);

	}
	
	
}
