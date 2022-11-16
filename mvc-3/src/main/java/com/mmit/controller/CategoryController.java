package com.mmit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mmit.entities.Category;
import com.mmit.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService service;
	
	@GetMapping("/categories")
	public String home(Model model) {
		var category = service.findAll();
		model.addAttribute("categoryList", category);
		return "category-list";
	}
	
	@GetMapping("/categories/add")
	public String goAddPage() {

		return "category-add";
	}
	
	@PostMapping("/categories/save")
	public String SaveCategory( @ModelAttribute("category" ) Category cat,BindingResult result) {
		
		if(result.hasErrors()) {
			return "category-add";
		}
		service.save(cat);
		return "redirect:/categories";
	}
	
	@GetMapping("/categories/edit/{catid}")
	public String editCategory(@PathVariable("catid") int CateId, Model m) {
		var category = service.findById(CateId);
		
		m.addAttribute("category", category);
		
		return "category-add";
	}
	
	@GetMapping("/categories/delete/{id}")
	public String deleteCategory(@PathVariable("id") int catId) {
		service.deleteById(catId);
		
		return "redirect:/categories";
	}
	
	@ModelAttribute
	public void assignDefual(Model m) {
		m.addAttribute("page" , "category");
		m.addAttribute("category", new Category());
	}
}
