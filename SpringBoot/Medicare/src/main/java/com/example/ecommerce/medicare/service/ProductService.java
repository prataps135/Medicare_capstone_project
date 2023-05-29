package com.example.ecommerce.medicare.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.ecommerce.medicare.exceptions.DeleteProductException;
import com.example.ecommerce.medicare.exceptions.ProductNotFound;
import com.example.ecommerce.medicare.model.Product;

@Component
public interface ProductService {
	Product addProduct(Product product);
	Product getProductById(int pid) throws ProductNotFound;
	Product updateProduct(int pid, Product product);
	List<Product> deleteProductById(int pid) throws DeleteProductException;
	List<Product> getAllProducts();
	List<Product> getByCategory(String pcat);
	Product getByLabelCode(String name);
}
