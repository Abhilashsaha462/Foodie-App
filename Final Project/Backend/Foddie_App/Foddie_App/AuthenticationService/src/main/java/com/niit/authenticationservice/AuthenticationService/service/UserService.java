package com.niit.authenticationservice.AuthenticationService.service;

import com.niit.authenticationservice.AuthenticationService.domain.User;
import com.niit.authenticationservice.AuthenticationService.exception.UserNotFoundException;


public interface UserService {

    public User saveUser(User user);

    public User findByEmailAndPassword(String email,String password) throws UserNotFoundException;


}
