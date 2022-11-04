package com.niit.AdminService.repository;

import com.niit.AdminService.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

}
