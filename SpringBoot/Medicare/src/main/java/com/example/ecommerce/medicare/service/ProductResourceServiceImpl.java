package com.example.ecommerce.medicare.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.medicare.model.ProductResource;
import com.example.ecommerce.medicare.repository.ProductResourceRepo;

@Service
public class ProductResourceServiceImpl implements ProductResourceService {
	
	@Autowired
	private ProductResourceRepo productResourceRepo;
	public List<ProductResource> getAllResources() {
		List<ProductResource> resourceList = new ArrayList<ProductResource>();
		resourceList = productResourceRepo.findAll().stream().collect(Collectors.toList());
		return resourceList;
	}

}
