package com.mmit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.entity.Category;
import com.mmit.repo.CategoryRepo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo repo;

	public List<Category> findAll() {
		
		return repo.findAll();
	}

	
	public Category findById(int cat) {
		
		return repo.findById(cat).orElse(new Category());
	}


	public Category save(Category cat) {
		
		return repo.save(cat);
	}
}
