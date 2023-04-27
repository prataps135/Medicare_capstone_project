package com.example.medicare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.medicare.model.Admin;
import com.example.medicare.model.Customer;

@Repository
public interface AdminRepo extends CrudRepository<Admin, Integer>{
	Admin findByName(String name);
	Admin findByEmailAndPassword(String email, String password);
	Admin findByEmail(String email);
}
