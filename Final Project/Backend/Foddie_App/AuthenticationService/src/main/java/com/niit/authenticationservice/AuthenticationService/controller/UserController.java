package com.niit.authenticationservice.AuthenticationService.controller;

import com.niit.authenticationservice.AuthenticationService.domain.User;
import com.niit.authenticationservice.AuthenticationService.exception.UserNotFoundException;
import com.niit.authenticationservice.AuthenticationService.service.EmailService;
import com.niit.authenticationservice.AuthenticationService.service.SecurityTokenGenerator;
import com.niit.authenticationservice.AuthenticationService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
@RequestMapping("/api/v3")
@RestController
public class UserController {

    private ResponseEntity responseEntity;
    private UserService userService;
    private SecurityTokenGenerator securityTokenGenerator;
    private EmailService emailService;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator, EmailService emailService) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
        this.emailService = emailService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws IOException {
        User createdUser = userService.saveUser(user);
        String status = emailService.sendSimpleEmail(user);
        return responseEntity = new ResponseEntity(status, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFoundException {
        System.out.println("");
        Map<String, String> map = null;
        try {
            User userObj = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
            if (userObj.getEmail().equals(user.getEmail())) {
                map = securityTokenGenerator.generateToken(user);
            }
            responseEntity = new ResponseEntity(map, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Try after sometime !!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/update")
    public ResponseEntity updateUserDetailsFunction(@RequestBody User user)throws UserNotFoundException {
        if (user.getPassword() == null) {
            return responseEntity = new ResponseEntity<>("Nothing to Save", HttpStatus.OK);
        } else {
            userService.updateUser(user);
            System.out.println("reached");
            return responseEntity = new ResponseEntity("User Updated", HttpStatus.CREATED);
        }
    }

}
