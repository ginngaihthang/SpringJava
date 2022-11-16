package com.mmit.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mmit.FileUploadUtil;
import com.mmit.entity.Category;
import com.mmit.entity.Product;
import com.mmit.model.entity.User;
import com.mmit.model.entity.UserRole;
import com.mmit.service.CategoryService;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService service;


	@GetMapping("/categories")
	public String categoryHome(Model m) {
		m.addAttribute("categoryList",service.findAll());
		return "category-list";
	}
	
	@GetMapping("/categories/add")
	public String addCategory() {
		
		return "category-add";
	}
	
	@PostMapping("/categories/save")
	public String saveCategory(@ModelAttribute("product") Category cat, @RequestParam("photo_file") MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename()); // admin.png
				
		if(cat.getId() == 0 || cat.getId()!= 0 && !fileName.equals("") ) 
			cat.setPhoto(fileName);
		
				
				
		var saveProduct = service.save(cat);
		if(!"".equals(fileName)) {
			String uploadDir = "uploads/" + saveProduct.getId();// upload-photos/1
			FileUploadUtil.savePhoto(uploadDir, fileName, file);
					
		}
		return "redirect:/categories";
	}
	
	@GetMapping("/categories/edit/{id}")
	public String editCategory(@PathVariable("id") int cat, Model m) {
		m.addAttribute("category", service.findById(cat));
		
		return "category-add";
	}
	
	@ModelAttribute
	public void assignDefaultModel(ModelMap map) {
		map.addAttribute("category", new Category());
		
	}
}
