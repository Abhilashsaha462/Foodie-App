package com.niit.authenticationservice.AuthenticationService.repository;

import com.niit.authenticationservice.AuthenticationService.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

    public User findByEmailAndPassword(String email , String password);

}
