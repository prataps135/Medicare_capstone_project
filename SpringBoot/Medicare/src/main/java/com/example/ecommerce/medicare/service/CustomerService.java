package com.example.ecommerce.medicare.service;

import org.springframework.stereotype.Component;

import com.example.ecommerce.medicare.model.Customer;

@Component
public interface CustomerService {
	Customer addCustomer(Customer cust);
	Customer getByEmail(String name);
	Customer getByEmailAndPassword(String email, String pass);
}
