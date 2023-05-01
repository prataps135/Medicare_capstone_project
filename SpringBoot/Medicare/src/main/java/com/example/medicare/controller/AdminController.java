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
import com.example.medicare.exception.AdminAlreadyExistsException;
import com.example.medicare.exception.AdminNotFoundException;
import com.example.medicare.exception.CustomerAlreadyExistsException;
import com.example.medicare.exception.LoginException;
import com.example.medicare.model.Admin;
import com.example.medicare.model.Customer;
import com.example.medicare.service.AdminService;
import jakarta.validation.Valid;

@RestController//(value="/admin")
@Validated
public class AdminController {
	@Autowired
	AdminService service;
	
//	@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
//	@PostMapping(value="admin/add")
//	public ResponseEntity<Admin> addAdmin(@Valid @RequestBody Admin admin){
//		Admin tempAdmin = service.addAdmin(admin);
//		return new ResponseEntity<Admin>(tempAdmin,HttpStatus.CREATED);
//	}
	

	@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
	@PostMapping(value="admin/add")
	public ResponseEntity<Admin> addAdmin(@Valid @RequestBody Admin admin)throws Exception{
		String tempEmail = admin.getEmail();
		if(tempEmail != null && !"".equals(tempEmail)) {
			Admin adminTemp = service.getByEmail(tempEmail);
			if(adminTemp != null) {
				throw new AdminAlreadyExistsException();
			}
		}
		Admin adminTemp = null;
		adminTemp = service.addAdmin(admin);
		return new ResponseEntity<Admin>(adminTemp,HttpStatus.CREATED);
	}
	
	@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
	@GetMapping(value = "admin/name/{name}")
	public ResponseEntity<Admin> getByName(@PathVariable String name) throws Exception{
		Admin admin = service.getByName(name);
		if(admin == null) {
			throw new AdminNotFoundException();
		}
		return new ResponseEntity<Admin>(admin,HttpStatus.FOUND);
	}
	
	@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
	@GetMapping(value = "admin/email/{email}")
	public ResponseEntity<Admin> getByEmail(@PathVariable("email") String email) throws Exception{
		Admin admin = service.getByEmail(email);
		if(admin == null) {
			throw new AdminNotFoundException();
		}
		return new ResponseEntity<Admin>(admin,HttpStatus.FOUND);
	}
	
	@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
	@PostMapping(value = "admin/login")
	public ResponseEntity<Admin> getByEmailAndPassword(@RequestBody Admin admin) throws Exception {
		String tempEmail = admin.getEmail();
		String tempPass = admin.getPassword();
		Admin adminObj = null;
		if (tempEmail != null && tempPass != null) {
			adminObj  = service.getByEmailAndPassword(tempEmail, tempPass);			
		}
		if (adminObj == null) {
			throw new LoginException();
		}
		return new ResponseEntity<Admin>(adminObj, HttpStatus.FOUND);
	}
}
