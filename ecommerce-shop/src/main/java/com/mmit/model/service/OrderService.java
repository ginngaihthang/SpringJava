package com.mmit.model.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.mmit.model.entity.Orders;
import com.mmit.model.repo.OrderRepo;


@Service
public class OrderService {

	@Autowired
	private OrderRepo repo;

	public Orders save(Orders new_order) {
		return repo.save(new_order);
	}


	
	public List<Orders> findAll() {
		return repo.findAll();
	}

	public Orders findById(long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}



	public List<Orders> findByCustomerId(int id) {
		return repo.findByCustomerId(id);
	}



	public void deleteById(long id) {
		repo.deleteById(id);
	}

	
	

	
	
}
