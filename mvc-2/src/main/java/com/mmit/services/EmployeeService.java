package com.mmit.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.entities.Employee;
import com.mmit.model.repo.EmployeeRepository;

import antlr.collections.List;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repo;

	public void save(Employee emp) {
		repo.save(emp);
		
	}

	public java.util.List<Employee> findAll() {
		
		return repo.findAll();
	}

	public Employee findById(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	public void deleteById(int empId) {
		repo.deleteById(empId);
		
	}
	
}
