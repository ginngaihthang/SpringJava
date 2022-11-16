package com.mmit.model.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mmit.FileUploadUtil;
import com.mmit.model.entity.Product;
import com.mmit.model.repo.ProductRepo;



@Service
public class ProductService {
	
	@Autowired
	private ProductRepo repo;

	public List<Product> findAll() {
		return repo.findAll();
	}

	public Product findById(long id) {
		return repo.findById(id).orElse(new Product());
	}

	public void deleteById(long id) {
		repo.deleteById(id);
	}
	
//	public long findCount()
//	{
//		return repo.getCount();
//	}

//	public List<Product> findByCategory(int id) {
//		return repo.findByCategory(id);
//	}

	public Product save(Product prod) {
		
		return repo.save(prod);
	}

	public List<Product> findbyCategoryId(int id) {
		
		return repo.findByCategoryId(id);
	}

	
}
