package com.example.medicare.service;

import org.springframework.stereotype.Component;
import com.example.medicare.model.Customer;

@Component
public interface CustomerService {
	Customer addCustomer(Customer cust);
	Customer getCustomerByName(String name);
	Customer getByEmail(String name);
	Customer getByEmailAndPassword(String email, String password);
}
