package com.niit.OrderServices.Repository;

import com.niit.OrderServices.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findByEmail(String email);
    User findByUserName(String userName);
}
