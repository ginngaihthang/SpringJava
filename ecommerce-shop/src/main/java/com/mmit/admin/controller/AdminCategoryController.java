package com.mmit.admin.controller;

import java.security.Principal;

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
import com.mmit.model.entity.Category;
import com.mmit.model.entity.User;
import com.mmit.model.entity.UserRole;
import com.mmit.model.service.CategoryService;
import com.mmit.model.service.UserService;

@Controller
public class AdminCategoryController {
	
	@Autowired
	private CategoryService service;
	@Autowired
	private UserService  userService;


	@GetMapping("admin/category")
	public String categoryHome(Model m) {
		m.addAttribute("categoryList",service.findAll());
		return "admin/category-list";
	}
	
	@GetMapping("/admin/category/add")
	public String addCategory() {
		
		return "admin/category-add";
	}
	
	@PostMapping("/admin/category/save")
	public String saveCategory(@ModelAttribute("category") Category cat, @RequestParam("photo_file") MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename()); // admin.png
				
		if(cat.getId() == 0 || cat.getId()!= 0 && !fileName.equals("") ) 
			cat.setPhoto(fileName);
			
		var saveProduct = service.save(cat);
		if(!"".equals(fileName)) {
			String uploadDir = "uploads/" + saveProduct.getId();// upload-photos/1
			FileUploadUtil.savePhoto(uploadDir, fileName, file);
					
		}
		return "redirect:/admin/category";
	}
	
	@GetMapping("/admin/category/edit/{id}")
	public String editCategory(@PathVariable("id") int cat, Model m) {
		m.addAttribute("category", service.findById(cat));
		return "admin/category-add";
	}
	
	@GetMapping("/admin/category/delete/{id}")
	public String deleteCategory(@PathVariable("id") int cate) {
		service.deleteById(cate);
		return "redirect:/admin/category";
	}
	
	@ModelAttribute
	public void assignDefaultModel(ModelMap map, Principal principal) {
		map.addAttribute("category", new Category());
		User loginuser = userService.profile(principal.getName());
		User Log = userService.findByRole(UserRole.admin);
		map.put("admin", loginuser);

	}
}
