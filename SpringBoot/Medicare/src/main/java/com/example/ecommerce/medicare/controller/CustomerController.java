package com.example.ecommerce.medicare.controller;

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
import com.example.ecommerce.medicare.exceptions.CustomerExistException;
import com.example.ecommerce.medicare.exceptions.CustomerNotFoundException;
import com.example.ecommerce.medicare.exceptions.LoginException;
import com.example.ecommerce.medicare.model.Customer;
import com.example.ecommerce.medicare.service.CustomerService;

import jakarta.validation.Valid;


@RestController
@Validated
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
	@PostMapping(value="/customer")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) throws Exception{
		String tempEmail = customer.getEmail();
		if(tempEmail != null && !"".equals(tempEmail)) {
			Customer customerObj = customerService.getByEmail(tempEmail);
			if(customerObj != null) {
				throw new CustomerExistException();
			}
		}
		Customer custObj = null;
		custObj = customerService.addCustomer(customer);
		return new ResponseEntity<Customer>(custObj, HttpStatus.CREATED);
	}
	
	@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
	@GetMapping(value="/customer/{email}")
	public ResponseEntity<Customer> getByCustomerEmail(@PathVariable("email") String email) throws Exception{
		Customer customer = customerService.getByEmail(email);
		if(customer == null) {
			throw new CustomerNotFoundException();
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
	@PostMapping(value="/login")
	public ResponseEntity<Customer> getByEmailAndPassword(@RequestBody Customer customer) throws Exception {
		String tempEmail = customer.getEmail();
		String tempPass = customer.getPassword();
		Customer customerObj = null;
		if (tempEmail != null && tempPass != null) {
			customerObj  = customerService.getByEmailAndPassword(tempEmail, tempPass);			
		}
		if (customerObj == null) {
			throw new LoginException();
		}
		return new ResponseEntity<Customer>(customerObj, HttpStatus.OK);
	}
}
