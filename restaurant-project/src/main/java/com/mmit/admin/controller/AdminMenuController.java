package com.mmit.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mmit.model.entity.Menu;
import com.mmit.model.service.MenuService;

@Controller
public class AdminMenuController {
	
	@Autowired
	private MenuService menuService;

	@GetMapping("/admin/menus")
	public String menuHome(ModelMap map) {
		map.put("menuList", menuService.findAll());
		return "admin/menu-list";
	}
	
	@GetMapping("/admin/menus/add")
	public String addMenu() {
		
		return "admin/menu-add";
	}
	
	@PostMapping("/admin/menus/save")
	public String saveMenu(@ModelAttribute("menu") Menu menu) {
		menuService.save(menu);
		return "redirect:/admin/menus";
		
	}
	
	@GetMapping("/admin/menus/edit/{id}")
	public String editMenu(@PathVariable("id") int id, ModelMap map) {
		map.put("menu", menuService.findById(id));
		
		return "admin/menu-add";
	}
	
	@GetMapping("/admin/menus/delete/{id}")
	public String deleteMenu(@PathVariable("id") int id) {
		menuService.deleteById(id);
		return "admin/menu-list";
	}
	
	@ModelAttribute
	public void assignDefaultModel(ModelMap map) {
		map.put("menu", new Menu());
	}
}
