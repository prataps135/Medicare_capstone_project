package com.example.medicare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.medicare.exception.CustomerAlreadyExistsException;
import com.example.medicare.exception.CustomerNotFoundException;
import com.example.medicare.exception.LoginException;
import com.example.medicare.model.Customer;
import com.example.medicare.service.CustomerService;
import jakarta.validation.Valid;

@RestController//(value="/customer")
@Validated
public class CustomerController {
	@Autowired
	private CustomerService service;
	
//	@PostMapping(value="/customer")
//	public void addCustomer(Customer cust) {
//		service.addCustomer(cust);
//	}
	
	@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
	@PostMapping(value="customer/add")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer cust)throws Exception{
		String tempEmail = cust.getEmail();
		if(tempEmail != null && !"".equals(tempEmail)) {
			Customer custTemp = service.getByEmail(tempEmail);
			if(custTemp != null) {
				throw new CustomerAlreadyExistsException();
			}
		}
		Customer custTemp = null;
		custTemp = service.addCustomer(cust);
		return new ResponseEntity<Customer>(cust,HttpStatus.CREATED);
	}
	
	@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
	@GetMapping(value = "customer/name/{name}")
	public ResponseEntity<Customer> getByName(@PathVariable String name) throws Exception{
		Customer cust = service.getCustomerByName(name);
		if(cust == null) {
			throw new CustomerNotFoundException();
		}
		return new ResponseEntity<Customer>(cust,HttpStatus.FOUND);
	}
	
	@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
	@GetMapping(value = "customer/email/{email}")
	public ResponseEntity<Customer> getByEmail(@PathVariable("email") String email) throws Exception{
		Customer custTemp = service.getByEmail(email);
		if(custTemp == null) {
			throw new CustomerNotFoundException();
		}
		return new ResponseEntity<Customer>(custTemp,HttpStatus.FOUND);
	}
	
	@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
	@PostMapping(value = "customer/login")
	public ResponseEntity<Customer> getByEmailAndPassword(@RequestBody Customer customer) throws Exception {
		String tempEmail = customer.getEmail();
		String tempPass = customer.getPassword();
		Customer customerObj = null;
		if (tempEmail != null && tempPass != null) {
			customerObj  = service.getByEmailAndPassword(tempEmail, tempPass);			
		}
		if (customerObj == null) {
			throw new LoginException();
		}
		return new ResponseEntity<Customer>(customerObj, HttpStatus.FOUND);
	}
}
