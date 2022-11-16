package com.mmit.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	//@RequestMapping(method = RequestMethod.GET, name = "/home")
	@GetMapping("/home")
	public String goHome() {
		
		return "return-type"; //logical name (template/return-type.html)
	}
	
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") int uid) {
		System.out.println("userId" + uid);
		return "void";
	}
	
	@GetMapping("/string")
	public String goString(Model m ,@RequestParam(name = "name", required = false) String uname,
			@RequestParam(name = "age") int age) {
		 m.addAttribute("time", LocalDate.now());
		 m.addAttribute("username",uname);
		 m.addAttribute("userage",age);
		return "string-page";
	}
	@GetMapping("/void") // view-name => void.html
	public void goVoid() {
		
	}
	@GetMapping("/mv")
	public ModelAndView goModelAndView() {
	
		ModelMap m = new ModelMap();
		m.addAttribute("date", LocalDate.now());
		m.put("msg", "This is <b> Spring Mvc</b>!");
		ModelAndView obj = new ModelAndView("view-and-model", m);
		
		return obj;
	}
}
