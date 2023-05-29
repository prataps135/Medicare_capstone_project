package com.example.ecommerce.medicare.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.ecommerce.medicare.model.Product;

public interface ProductRepo extends CrudRepository<Product, Integer> {
	List<Product> findAll(); 
	Product getByLabelCode(String name);
}
