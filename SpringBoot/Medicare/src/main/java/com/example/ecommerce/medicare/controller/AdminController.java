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
import com.example.ecommerce.medicare.exceptions.AdminExistException;
import com.example.ecommerce.medicare.exceptions.AdminNotFoundException;
import com.example.ecommerce.medicare.exceptions.LoginException;
import com.example.ecommerce.medicare.model.Admin;
import com.example.ecommerce.medicare.service.AdminService;
import jakarta.validation.Valid;

@RestController
@Validated
public class AdminController {
	@Autowired
	AdminService adminService;
	
	@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
	@PostMapping(value="/adminUser")
	public ResponseEntity<Admin> addAdmin(@Valid @RequestBody Admin admin) throws Exception{
		String emailId = admin.getEmailId();
		if(emailId != null && !"".equals(emailId)) {
			Admin adm = adminService.getByEmailId(emailId);
			if(adm != null) {
				throw new AdminExistException();
			}
		}
		Admin adminObj = null;
		adminObj = adminService.addAdmin(admin);
		return new ResponseEntity<Admin>(adminObj, HttpStatus.CREATED);
	}
	
	@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
	@GetMapping(value="/adminUser/{emailId}")
	public ResponseEntity<Admin> getByEmailId(@PathVariable("emailId") String emaiId) throws Exception{
		Admin admin = adminService.getByEmailId(emaiId);
		if(admin == null) {
			throw new AdminNotFoundException();
		}
		return new ResponseEntity<Admin>(admin, HttpStatus.OK);
	}
	
	@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
	@PostMapping(value="/adminLogin")
	public ResponseEntity<Admin> getByEmailIdAndPassword(@RequestBody Admin admin) throws Exception {
		String tempEmailId = admin.getEmailId();
		String tempPwd = admin.getPassword();
		Admin admObj = null;
		if (tempEmailId != null && tempPwd != null) {
			admObj = adminService.getByEmailIdAndPassword(tempEmailId, tempPwd);			
		}
		if (admObj == null) {
			throw new LoginException();
		}
		return new ResponseEntity<Admin>(admObj, HttpStatus.OK);
	}
}
