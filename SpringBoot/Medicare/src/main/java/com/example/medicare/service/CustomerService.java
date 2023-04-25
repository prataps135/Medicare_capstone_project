package com.example.medicare.service;

import org.springframework.stereotype.Component;
import com.example.medicare.model.Customer;

@Component
public interface CustomerService {
	void addCustomer(Customer cust);
	Customer getCustomerByName(String name);
}
