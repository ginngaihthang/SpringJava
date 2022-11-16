package com.mmit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.model.entity.Menu;
import com.mmit.model.repo.MenuRepo;

@Service
public class MenuService {

	@Autowired
	private MenuRepo repo;

	public List<Menu> findAll() {
		
		return repo.findAll();
	}

	public void save(Menu menu) {
		repo.save(menu);
		
	}

	public Menu findById(int id) {
		
		return repo.findById(id).orElse(new Menu());
	}

	public void deleteById(int id) {
		repo.deleteById(id);
		
	}
}
