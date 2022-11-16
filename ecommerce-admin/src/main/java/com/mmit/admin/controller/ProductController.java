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
import com.mmit.entity.Product;
import com.mmit.service.CategoryService;
import com.mmit.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/products")
	public String home(ModelMap map) {
		List<Product> list = productService.findAll();
		map.put("productList", list);
		return "admin/product-list";
	}
	
	@GetMapping("/products/add")
	public String goAdd(Model m) {
		m.addAttribute("categories", categoryService.findAll());
		return "admin/product-add";
	}
	
	@PostMapping("/products/save")
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
		return "redirect:admin/products";
	}
	
	@GetMapping("/products/edit/{id}")
	public String editProduct(@PathVariable("id") long productId, Model m) {
		var prod = productService.findById(productId);
		m.addAttribute("categories", categoryService.findAll());
		m.addAttribute("product", prod);
		
		return "admin/product-add";
	}
	
	@ModelAttribute
	public void assignDefualModel(Model m) {
		m.addAttribute("product", new Product());
	}
}
