package com.example.ecommerce.medicare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.medicare.model.Admin;
import com.example.ecommerce.medicare.repository.AdminRepo;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepo adminRepo;

	public Admin addAdmin(Admin admin) {
		return adminRepo.save(admin);
	}

	public Admin getByEmailId(String email) {
		return adminRepo.findByEmailId(email);
	}

	public Admin getByEmailIdAndPassword(String eid, String pass) {
		return adminRepo.findByEmailIdAndPassword(eid, pass);
	}

}
