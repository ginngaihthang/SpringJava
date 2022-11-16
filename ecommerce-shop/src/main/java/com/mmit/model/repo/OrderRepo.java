package com.mmit.model.repo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mmit.model.entity.Orders;


public interface OrderRepo extends JpaRepository<Orders, Long> {
	

	@Query("SELECT o FROM Orders o WHERE o.customer.id = :id")
	List<Orders> findByCustomerId(@Param("id") int id);

	@Query("Select sum(amount) From Orders")
	Orders findAllTotalAmount();
}
