package com.mmit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.entity.Orders;
import com.mmit.repo.OrderRepo;
@Service
public class OrderService {

	@Autowired
	private OrderRepo repo;

	public Orders save(Orders new_order) {
		// TODO Auto-generated method stub
		return repo.save(new_order);
	}
}
