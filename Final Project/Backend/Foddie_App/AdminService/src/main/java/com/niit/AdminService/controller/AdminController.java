package com.niit.AdminService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.AdminService.domain.Admin;
import com.niit.AdminService.domain.Restaurant;
import com.niit.AdminService.domain.User;
import com.niit.AdminService.exception.AdminAlreadyExistsException;
import com.niit.AdminService.exception.AdminNotFoundException;
import com.niit.AdminService.exception.RestaurantAlreadyExistsException;
import com.niit.AdminService.exception.UserAlreadyExistsException;
import com.niit.AdminService.service.AdminService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/admin-service")
public class AdminController {

    private AdminService adminService;


    private ResponseEntity responseEntity;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register")
    public  ResponseEntity<?> registerUser(@RequestBody Admin admin) throws AdminAlreadyExistsException {
        return new ResponseEntity<>(adminService.registerUser(admin), HttpStatus.CREATED);
    }

    @PostMapping("/admin/restaurantName")
    public ResponseEntity<?> addNewRestaurant(@RequestParam("file") MultipartFile file, @RequestParam("details") String details)throws RestaurantAlreadyExistsException {
        try {
            System.out.println("Controller");
            Restaurant restaurant = new ObjectMapper().readValue(details,Restaurant.class);
//            Restaurant restaurant = gson.fromJson(details,Restaurant.class);
            responseEntity = new ResponseEntity(adminService.addRestaurant(restaurant,file.getBytes()), HttpStatus.CREATED);
        }catch (RestaurantAlreadyExistsException e){
            throw new RestaurantAlreadyExistsException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseEntity;
    }

    @DeleteMapping("/admin/deleteRestaurant/{restId}")
    public  ResponseEntity<?> deleteRestaurant(@PathVariable int restid){
        return new ResponseEntity<>(adminService.removeRestaurant(restid), HttpStatus.OK);
    }

    @PutMapping("/updateRestaurant/{restaurantId}")
    public ResponseEntity<?> updateRestaurant(@PathVariable String restaurantId,@RequestBody Restaurant restaurant){
        return new ResponseEntity<>(adminService.updateRestaurant(restaurantId,restaurant), HttpStatus.OK);
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> addUser(@RequestBody User user) throws UserAlreadyExistsException {
        return new ResponseEntity<>(adminService.addUser(user), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Admin admin) throws AdminNotFoundException {
       // System.out.println(admin);
        Map<String, String> map = null;
        try {
            Admin userObj = adminService.findByEmailAndPasswrd(admin.getEmail(),admin.getPassword());

            System.out.println("here"+userObj);
                responseEntity = new ResponseEntity(admin, HttpStatus.OK);

        } catch (AdminNotFoundException e) {
            throw new AdminNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Try after sometime !!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
