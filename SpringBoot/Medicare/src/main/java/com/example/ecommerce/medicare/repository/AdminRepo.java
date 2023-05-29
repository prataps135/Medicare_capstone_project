package com.example.ecommerce.medicare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.ecommerce.medicare.model.Admin;

@Repository
public interface AdminRepo extends CrudRepository<Admin, Integer>{
	Admin findByEmailId(String name);
	Admin findByEmailIdAndPassword(String email, String password);
}
