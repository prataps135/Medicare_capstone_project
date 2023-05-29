package com.example.ecommerce.medicare.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.ecommerce.medicare.model.ProductResource;

public interface ProductResourceRepo extends CrudRepository<ProductResource, String> {
	List<ProductResource> findAll();
}
