package com.niit.authenticationservice.AuthenticationService.Consumer;

import com.niit.authenticationservice.AuthenticationService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    private UserService userService;
}
