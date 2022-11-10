package com.niit.AdminService.repository;

import com.niit.AdminService.domain.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin,String> {

}
