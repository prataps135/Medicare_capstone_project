package com.example.ecommerce.medicare.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.ecommerce.medicare.model.ProductResource;

@Component
public interface ProductResourceService {
	List<ProductResource> getAllResources();
}
