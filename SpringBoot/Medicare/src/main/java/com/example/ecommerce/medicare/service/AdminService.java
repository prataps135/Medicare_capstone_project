package com.example.ecommerce.medicare.service;

import org.springframework.stereotype.Component;
import com.example.ecommerce.medicare.model.Admin;


@Component
public interface AdminService {
	Admin addAdmin(Admin admin);
	Admin getByEmailId(String name);
	Admin getByEmailIdAndPassword(String email, String pass);
}
