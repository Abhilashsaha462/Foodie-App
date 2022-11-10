package com.niit.authenticationservice.AuthenticationService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.authenticationservice.AuthenticationService.domain.EmailDetails;
import com.niit.authenticationservice.AuthenticationService.domain.User;
import com.niit.authenticationservice.AuthenticationService.exception.UserNotFoundException;
import com.niit.authenticationservice.AuthenticationService.service.EmailService;
import com.niit.authenticationservice.AuthenticationService.service.SecurityTokenGenerator;
import com.niit.authenticationservice.AuthenticationService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.DataInput;
import java.io.IOException;
import java.util.Map;
@RequestMapping("/api/v3")
@RestController
public class UserController {

    private ResponseEntity responseEntity;
    private UserService userService;
    private SecurityTokenGenerator securityTokenGenerator;
    @Autowired
    private EmailService emailService;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws IOException {
        User createdUser = userService.saveUser(user);
        String status = emailService.sendSimpleEmail(user);
        return responseEntity = new ResponseEntity(status, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFoundException {
        System.out.println(user);
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
       try {
           System.out.println("reached");
           userService.updateUser(user);
           responseEntity = new ResponseEntity("User Updated", HttpStatus.CREATED);
       }catch (UserNotFoundException e){
           throw new UserNotFoundException();
       }
       return responseEntity;

    }
    @PutMapping("/forgot")
    public ResponseEntity forgotPassword(@RequestBody User user)throws UserNotFoundException{
        try {
            responseEntity = new ResponseEntity(userService.forgotPassword(user),HttpStatus.OK);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

}
