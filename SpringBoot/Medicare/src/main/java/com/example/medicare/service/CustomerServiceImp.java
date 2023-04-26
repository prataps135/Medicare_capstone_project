package com.example.medicare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.support.RepositoryComposition;
import org.springframework.stereotype.Service;

import com.example.medicare.model.Customer;
import com.example.medicare.repository.CustomerRepo;

@Service
public class CustomerServiceImp implements CustomerService{
	
	@Autowired
	private CustomerRepo repo;
	
	@Override
	public Customer addCustomer(Customer cust) {
		return repo.save(cust);
	}
	
	@Override
	public Customer getCustomerByName(String name) {
		Customer cust;
		cust = repo.findByName(name);
		return cust;
	}

	@Override
	public Customer getByEmailAndPassword(String email, String password) {
		return repo.findByEmailAndPassword(email, password);
	}

	@Override
	public Customer getByEmail(String name) {
		return repo.findByEmail(name);
	}
}
