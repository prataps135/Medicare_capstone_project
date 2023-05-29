package com.example.ecommerce.medicare.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.ecommerce.medicare.model.ProductResource;
import com.example.ecommerce.medicare.service.ProductResourceService;

@RestController
public class ProductResourceController {
	
	@Autowired
	private ProductResourceService productResourceService;
	
	@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
	@GetMapping(value="/productresources")
	public ResponseEntity<List<ProductResource>> getAllResources() {
		   List<ProductResource> resourceList = productResourceService.getAllResources();
		   return new ResponseEntity<>(resourceList, HttpStatus.OK);
	}
}
