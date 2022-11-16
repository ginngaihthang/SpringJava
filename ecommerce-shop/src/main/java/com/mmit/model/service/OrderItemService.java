package com.mmit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.model.entity.OrderItem;
import com.mmit.model.entity.Orders;
import com.mmit.model.repo.OrderItemRepo;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemRepo repo;
	
	public List<OrderItem> findByProductID(int id)
	{
		return repo.findByProductID(id);
	}

	public List<OrderItem> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public void deleteById(int id) {
		repo.deleteById(id);
	}

	public OrderItem findbyOrderId(long id) {
		// TODO Auto-generated method stub
		return repo.findByOrderId(id);
	}

	public OrderItem findById(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(new OrderItem());
	}

	
}
