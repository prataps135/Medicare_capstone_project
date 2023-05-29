package com.example.ecommerce.medicare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.medicare.model.Customer;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Integer> {
	Customer findByEmail(String name);
	Customer findByEmailAndPassword(String email, String password);
}
