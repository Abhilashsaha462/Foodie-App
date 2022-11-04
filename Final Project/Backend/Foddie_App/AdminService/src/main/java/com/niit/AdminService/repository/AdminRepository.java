package com.niit.AdminService.repository;

import com.niit.AdminService.domain.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Map;

public interface AdminRepository extends MongoRepository<Admin,String> {
    Admin findByEmailAndPassword(String email,String password);

}
