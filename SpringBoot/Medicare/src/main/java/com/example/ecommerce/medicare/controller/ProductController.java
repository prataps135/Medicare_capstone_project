package com.example.ecommerce.medicare.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.ecommerce.medicare.exceptions.CategoryException;
import com.example.ecommerce.medicare.exceptions.DeleteProductException;
import com.example.ecommerce.medicare.exceptions.ProductExistException;
import com.example.ecommerce.medicare.exceptions.ProductNotFound;
import com.example.ecommerce.medicare.exceptions.UpdateProductException;
import com.example.ecommerce.medicare.model.Product;
import com.example.ecommerce.medicare.service.ProductService;

@RestController
public class ProductController {
	
   @Autowired
	private ProductService productService;
   
    @CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
	@PostMapping(value="/addproduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) throws Exception{
    	String tempLabelName = product.getLabelCode();
		if(tempLabelName != null && !"".equals(tempLabelName)) {
			Product productObj = productService.getByLabelCode(tempLabelName);
		    if(productObj != null ) {
		    	throw new ProductExistException();
		    }
		}
    	Product productobj = null;
		 productobj = productService.addProduct(product);
		return new ResponseEntity<Product>(productobj, HttpStatus.CREATED);
	}
    
    @CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
    @GetMapping(value="/products/{pid}")
	 public ResponseEntity<Product> getProductById(@PathVariable("pid") int pid) throws ProductNotFound {
			  Product product = productService.getProductById(pid);
			  return new ResponseEntity<Product>(product, HttpStatus.OK);	
	 }
    
    @CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
    @PutMapping(value="updateproduct/{pid}")
     public ResponseEntity<Product> updateProduct(@PathVariable int pid, @RequestBody Product product) throws Exception {
    	Product prod = productService.updateProduct(pid, product);
    	if (prod != null ) {
    		return new ResponseEntity<Product>(prod, HttpStatus.OK);
    	} else {
    		throw new UpdateProductException();
    	}
    }
    
    @CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
    @DeleteMapping(value="/deleteproduct/{pid}")
	public ResponseEntity<List<Product>> deleteProductById(@PathVariable("pid") int pid) throws DeleteProductException {
    		List<Product> productList = productService.deleteProductById(pid);
    		return new ResponseEntity<>(productList, HttpStatus.ACCEPTED);
	}
    
    @CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
    @GetMapping(value="/getallproducts")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> productList = productService.getAllProducts();
		return new ResponseEntity<>(productList, HttpStatus.OK);
	} 
    
    @CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
	@GetMapping(value="/products/category/{category}")
    public ResponseEntity<List<Product>> getByCategory( @PathVariable("category") String pcat) throws Exception{
		  List<Product> prodList = productService.getByCategory(pcat);
		  if (!prodList.isEmpty()) {
			  return new ResponseEntity<>(prodList, HttpStatus.OK);
		  } else {
			  throw new CategoryException();
		  }
     }
}
