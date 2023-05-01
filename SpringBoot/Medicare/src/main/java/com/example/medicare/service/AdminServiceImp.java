package com.example.medicare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.medicare.model.Admin;
import com.example.medicare.repository.AdminRepo;

@Service
public class AdminServiceImp implements AdminService{
	
	@Autowired
	AdminRepo repo;
	
	@Override
	public Admin addAdmin(Admin admin) {
		return repo.save(admin);
	}

	@Override
	public Admin getByEmail(String email) {
		return repo.findByEmail(email);
	}

	@Override
	public Admin getByEmailAndPassword(String email, String password) {
		return repo.findByEmailAndPassword(email, password);
	}

	@Override
	public Admin getByName(String name) {
		return repo.findByName(name);
	}

}
