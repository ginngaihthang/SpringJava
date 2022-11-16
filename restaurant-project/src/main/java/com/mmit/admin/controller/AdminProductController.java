package com.mmit.admin.controller;

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
import com.mmit.model.service.MenuService;
import com.mmit.model.service.ProductService;

@Controller
public class AdminProductController {
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private ProductService productService;
	
	
	@GetMapping("/admin/products")
	public String goHome(ModelMap map) {
		List<Product> list = productService.findAll();
		map.put("productList", list);
		return "admin/product-list";
	}
	
	@GetMapping("/admin/products/add")
	public String addProduct(ModelMap map) {
		map.put("menus", menuService.findAll());
		return "admin/product-add";
	}
	
	@PostMapping("/admin/products/save")
	public String saveProduct(@ModelAttribute("product") Product prod, @RequestParam("photo_file") MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		if(prod.getId() == 0 || prod.getId() != 0 && !fileName.equals("")) {
			prod.setPhoto(fileName);
			
		}
		
		var saveProduct = productService.save(prod);
		if(!"".equals(fileName)) {
			String uploadDir = "uploads/" + saveProduct.getId(); 
			FileUploadUtil.savePhoto(uploadDir, fileName, file);
		}
		
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/products/edit/{id}")
	public String editProduct(@PathVariable("id") long id,ModelMap map) {
		var prod = productService.findById(id);
		var menu = menuService.findAll();
		map.put("product", prod);
		map.put("menus", menu);
		return "admin/product-add";
	}
	
	@GetMapping("/admin/products/delete/{id}")
	public String deleteProduct(@PathVariable("id") long id) {
		productService.deleteById(id);
		return "admin/product-list";
	}

	@ModelAttribute
	public void asignDefaultModel(Model m) {
		m.addAttribute("product", new Product());
	}
}
