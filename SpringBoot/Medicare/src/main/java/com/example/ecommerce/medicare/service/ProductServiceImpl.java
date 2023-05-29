package com.example.ecommerce.medicare.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.medicare.exceptions.DeleteProductException;
import com.example.ecommerce.medicare.exceptions.ProductNotFound;
import com.example.ecommerce.medicare.model.Product;
import com.example.ecommerce.medicare.repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepo productRepo;

	public Product addProduct(Product product) {
		return productRepo.save(product);
	}

	public Product getProductById(int pid) throws ProductNotFound {
		return productRepo.findById(pid).orElseThrow(() -> new ProductNotFound());
	}

	public Product updateProduct(int pid, Product product) {
		if(productRepo.findById(pid).isPresent()) {
			Product oldProd= productRepo.findById(pid).get();
			oldProd.setPdesc(product.getPdesc());
			oldProd.setPcat(product.getPcat());
			oldProd.setPrice(product.getPrice());	
			return productRepo.save(oldProd);
		} else {
			return null;
		}
	}

	public List<Product> deleteProductById(int pid) throws DeleteProductException {
		List<Product> prodList = new ArrayList<Product>();
		if(productRepo.findById(pid).isPresent()) {
			productRepo.deleteById(pid);
			prodList = productRepo.findAll().stream().collect(Collectors.toList());
			return prodList;
		} else {
			throw new DeleteProductException();
		}
	}

	public List<Product> getAllProducts() {
		List<Product> prodList = new ArrayList<Product>();
		prodList = productRepo.findAll().stream().collect(Collectors.toList());
		return prodList;
	}

	public List<Product> getByCategory(String pcat) {
		List<Product> prodList = new ArrayList<Product>();
		prodList = productRepo.findAll().stream().
				filter(val -> val.getPcat().equals(pcat)).collect(Collectors.toList());
		return prodList;
	}

	public Product getByLabelCode(String name) {
		return productRepo.getByLabelCode(name);
	}
}
