package com.example.ecommerce.medicare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.medicare.model.Customer;
import com.example.ecommerce.medicare.repository.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepo customerRepo;
	
	public Customer addCustomer(Customer cust) {
		return customerRepo.save(cust);
	}
	
	public Customer getByEmail(String email) {
		return customerRepo.findByEmail(email);
	}

	public Customer getByEmailAndPassword(String eid, String pwd) {
		return customerRepo.findByEmailAndPassword(eid, pwd);
	}
}
