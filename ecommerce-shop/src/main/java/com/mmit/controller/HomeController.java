package com.mmit.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mmit.FileUploadUtil;
import com.mmit.model.entity.Orders;
import com.mmit.model.entity.User;
import com.mmit.model.service.CategoryService;
import com.mmit.model.service.OrderItemService;
import com.mmit.model.service.OrderService;
import com.mmit.model.service.ProductService;
import com.mmit.model.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private ProductService proService;
	@Autowired
	private CategoryService catService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderItemService orderitemService;
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/")
	public String home() {
		
		return "redirect:/shop";
	}
	
	@GetMapping("/shop")
	public String index(ModelMap map) {
		map.put("productList", proService.findAll());
		map.put("categoryList", catService.findAll());
		return "index";
	}
	
	@GetMapping("/shop/category/{id}")
	public String findCategory(@PathVariable("id") int id, ModelMap map) {
		map.put("categoryList", catService.findAll());
		map.put("productList", proService.findbyCategoryId(id));
		return "index";
	}
	
	@GetMapping("/about")
	public String about() {
		
		return "about";
	}
	
	@GetMapping("/contact")
	public String contact() {
		
		return "contact";
	}
	
	@GetMapping("/shop/products/{id}")
	private String goSingleProduct(@PathVariable("id") long proId, ModelMap map) {
		map.put("product", proService.findById(proId));
		return "product-detail";
	}
	
	
	@GetMapping("/shop/orders")
	public String myOrderPage(ModelMap map,Principal principal) {
		
			User loginUser = userService.profile(principal.getName());
			System.err.println(" principal1 : " + loginUser);
			var orders = orderService.findByCustomerId(loginUser.getId());		
			map.put("orderItems", orders); 
		return "my-order";
	}
	
	@GetMapping("/shop/orders/delete/{id}")
	public String deleteMyOrder(@PathVariable("id") int id) {
		
		orderitemService.deleteById(id);
		return "redirect:/shop/orders";
	}
	
	@GetMapping("/register")
	public String Register(ModelMap map) {
		map.put("customerroles",  new String[] {"customer"});
		return "register";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		
		return "login";
	}
	
	@PostMapping("/shop/user/save")
	public String saveUser(@ModelAttribute("user") User user,  @RequestParam("photo_file") MultipartFile file ) {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename()); // admin.png
		
		if(user.getId() == 0 || user.getId()!= 0 && !fileName.equals("") ) {
			user.setPhoto(fileName);
		}
		
		
		var saveUser = userService.save(user);
	if(!"".equals(fileName)) {
			String uploadDir = "uploads/" + saveUser.getId();// upload-photos/1
			FileUploadUtil.savePhoto(uploadDir, fileName, file);
			
		}
		return "redirect:/shop";
	}
	
	@GetMapping("/shop/profile")
	public String profile(ModelMap map, Principal principal) {
		var loginUser = userService.profile(principal.getName());
		map.put("loginUser", loginUser);
		return "profile";
	}
	
	@GetMapping("/shop/changePassword/{id}")
	public  String changePassword(@PathVariable("id") int id, ModelMap map) {
		map.put("customerroles", new String[] {"customer"});
		map.put("changePass", userService.findById(id));
		return "change-password";
	}
	
	@GetMapping("/shop/changePhoto/{id}")
	public String ChangePhoto(@PathVariable("id") int id , ModelMap map) {
		map.put("customerroles", new String[] {"customer"});
		map.put("changePass", userService.findById(id));
		return "change-photo";
	}
	
	@ModelAttribute
	public void defaultAssign(ModelMap map) {
		map.put("user", new User());
	}

}
