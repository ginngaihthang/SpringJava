package com.mmit.admin.controller;
import java.security.Principal;
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
import com.mmit.model.entity.Category;
import com.mmit.model.entity.User;
import com.mmit.model.entity.UserRole;
import com.mmit.model.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;

	@GetMapping("/admin/customer")
	public String CustomerHome(ModelMap map) {
		map.put("customerList", service.findAll());
		return "admin/customer-list";
	}
	
	@GetMapping("/admin/customer/add")
	public String CustomerAdd(ModelMap map) {
		map.put("roles",new  String[]{"admin", "customer"});
		return "admin/customer-add";
	}
	
	@PostMapping("/admin/customer/save")
	public String saveCustomer(@ModelAttribute("customer") User cust, @RequestParam("photo_file") MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename()); // admin.png
				
		if(cust.getId() == 0 || cust.getId()!= 0 && !fileName.equals("") ) 
			cust.setPhoto(fileName);
			
		var saveProduct = service.save(cust);
		if(!"".equals(fileName)) {
			String uploadDir = "uploads/" + saveProduct.getId();// upload-photos/1
			FileUploadUtil.savePhoto(uploadDir, fileName, file);
					
		}
		return "redirect:/admin/customer";
	}	
	
	@GetMapping("/admin/customer/delete/{id}")
	public String deleteCustomer(@PathVariable("id") int id) {
		service.deleteById(id);
		return "redirect:/admin/customer";
	}
	
	@GetMapping("/admin/profile")
	public String profile(ModelMap map, Principal principal) {
		System.err.println("principal : " + principal);
		User loginuser = service.profile(principal.getName());
		User Log = service.findByRole(UserRole.admin);
		System.err.println("Login User : " + Log);
		map.put("admin", loginuser);
		map.put("cust", Log);
		return "admin/profile";
	}
	
	@GetMapping("/admin/changePassword/{id}")
	public String changPassword(@PathVariable("id") int id, ModelMap map) {
		map.put("roles", new String[] {"admin"});
		map.put("changePass", service.findById(id));
		return "admin/sitting";
	}
	
	@ModelAttribute
	public void Defaultassign(ModelMap map,Principal principal) {
		map.put("customer", new User());
		User loginuser = service.profile(principal.getName());
//		User Log = service.findByRole(UserRole.admin);
		map.put("admin", loginuser);
//		map.put("cust", Log);
	}
}
