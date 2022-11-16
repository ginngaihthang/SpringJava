package com.mmit.admin.controller;

import java.security.Principal;
import java.util.List;

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
import com.mmit.model.entity.Product;
import com.mmit.model.entity.User;
import com.mmit.model.entity.UserRole;
import com.mmit.model.service.CategoryService;
import com.mmit.model.service.ProductService;
import com.mmit.model.service.UserService;

@Controller
public class AdminProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserService service;
	
	@GetMapping("admin/product")
	public String home(ModelMap map,Principal principal) {
		User loginuser = service.profile(principal.getName());
		User Log = service.findByRole(UserRole.admin);
		System.err.println("Login User : " + Log);
		map.put("admin", loginuser);
		map.put("cust", Log);
		List<Product> list = productService.findAll();
		map.put("productList", list);
		return "admin/product-list";
	}
	
	@GetMapping("admin/product/add")
	public String goAdd(Model m) {
		m.addAttribute("categories", categoryService.findAll());
		return "admin/product-add";
	}
	
	@PostMapping("/admin/product/save")
	public String saveProduct(@ModelAttribute("product") Product prod, @RequestParam("photo_file") MultipartFile file) {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename()); // admin.png
		
		if(prod.getId() == 0 || prod.getId()!= 0 && !fileName.equals("") ) {
			prod.setPhoto(fileName);
		}
		
		
		var saveProduct = productService.save(prod);
	if(!"".equals(fileName)) {
			String uploadDir = "uploads/" + saveProduct.getId();// upload-photos/1
			FileUploadUtil.savePhoto(uploadDir, fileName, file);
			
		}
		return "redirect:/admin/product";
	}
	
	@GetMapping("/admin/product/edit/{id}")
	public String editProduct(@PathVariable("id") long productId, Model m) {
		var prod = productService.findById(productId);
		m.addAttribute("categories", categoryService.findAll());
		m.addAttribute("product", prod);
		
		return "admin/product-add";
	}
	
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable("id") long productId) {
		productService.deleteById(productId);
		return "redirect:/admin/product";
	}
	
	@ModelAttribute
	public void assignDefualModel(ModelMap map, Principal principal) {
		map.addAttribute("product", new Product());
		User loginuser = service.profile(principal.getName());
//		User Log = service.findByRole(UserRole.admin);
		map.put("admin", loginuser);
//		map.put("cust", Log);
	}
}
