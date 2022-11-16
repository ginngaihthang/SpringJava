package com.mmit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mmit.FileUploadUtil;
import com.mmit.model.entity.Category;
import com.mmit.model.repo.CategoryRepo;


@Service
public class CategoryService {

	@Autowired
	private CategoryRepo repo;

	public List<Category> findAll() {
		return repo.findAll();
	}

	public Category findById(int id) {
		return repo.findById(id).orElse(null);
	}


	public void deleteById(int id) {
		repo.deleteById(id);
	}

//	public long findCount() {
//		return repo.getCount();
//	}

	public Category save(Category cat) {
		
		return repo.save(cat);
	}
}
