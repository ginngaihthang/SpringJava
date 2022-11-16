package com.mmit.model.service;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mmit.model.entity.User;
import com.mmit.model.entity.UserRole;
import com.mmit.model.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo repo;
	@Autowired
	private PasswordEncoder encoder;

	public User save(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return repo.save(user);
	}
	
	
	public long countUser() {

		return repo.count();
	}


	public User profile(String email) {
		
		return repo.findUserByEmail(email);
	}


	public List<User> findAll() {
		
		return repo.findAll();
	}


	public void deleteById(int id) {
		repo.deleteById(id);
	}


	public User findByRole(UserRole admin) {
		
		return repo.findByRole(admin);
	}


	public User findById(int id) {
		
		return repo.findById(id).orElse(new User());
	}


	


}
