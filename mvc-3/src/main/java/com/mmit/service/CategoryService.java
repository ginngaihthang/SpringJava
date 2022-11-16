package com.mmit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.entities.Category;
import com.mmit.reposity.Categoryrepo;

@Service
public class CategoryService {

	@Autowired
	private Categoryrepo repo;

	public List<com.mmit.entities.Category> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public Category save(Category cat) {
		
		return repo.save(cat);
	}

	public Category findById(int cateId) {
		
		return repo.findById(cateId).get();
	}

	public void deleteById(int catId) {
	
		repo.deleteById(catId);
	}

	
}
