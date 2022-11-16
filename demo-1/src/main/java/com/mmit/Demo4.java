package com.mmit;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mmit.beans.Customer;
import com.mmit.beans.CustomerRepo;
import com.mmit.beans.CustomerService;
import com.mmit.config.BeanConfig;

public class Demo4 {

	public static void main(String[] args) {
		var cxt = new AnnotationConfigApplicationContext(BeanConfig.class);
		
//		var customerSer = cxt.getBean(CustomerService.class);
//		
//		var cust = customerSer.findById(1);
//		
//		System.out.println(cust);
		
		var custRepo = cxt.getBean(CustomerRepo.class);
		
		custRepo.save(new Customer(111, "Jeaon"));
		custRepo.save(new Customer(222, "chery"));
		
		var cust = custRepo.findBuId(11);
		System.out.println(cust);
	}
}
