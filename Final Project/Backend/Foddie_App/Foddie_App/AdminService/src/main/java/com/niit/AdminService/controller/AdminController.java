package com.niit.AdminService.controller;

import com.niit.AdminService.domain.Admin;
import com.niit.AdminService.domain.Restaurant;
import com.niit.AdminService.domain.User;
import com.niit.AdminService.exception.AdminAlreadyExistsException;
import com.niit.AdminService.exception.RestaurantAlreadyExistsException;
import com.niit.AdminService.exception.UserAlreadyExistsException;
import com.niit.AdminService.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin-service")
public class AdminController {

    private AdminService adminService;

    ResponseEntity responseEntity;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register")
    public  ResponseEntity<?> registerUser(@RequestBody Admin admin) throws AdminAlreadyExistsException {
        return new ResponseEntity<>(adminService.registerUser(admin), HttpStatus.OK);
    }

    @PostMapping("/admin/restaurantName")
    public ResponseEntity<?> addRestaurant(@RequestBody Restaurant restaurant) throws RestaurantAlreadyExistsException {
        return new ResponseEntity<>(adminService.addRestaurant(restaurant), HttpStatus.OK);
    }

    @DeleteMapping("/admin/deleteRestaurant/{email}")
    public  ResponseEntity<?> deleteRestaurant(@PathVariable String email){
        return new ResponseEntity<>(adminService.removeRestaurant(email), HttpStatus.OK);
    }

    @PutMapping("/updateRestaurant/{restaurantId}")
    public ResponseEntity<?> updateRestaurant(@PathVariable String restaurantId,@RequestBody Restaurant restaurant){
        return new ResponseEntity<>(adminService.updateRestaurant(restaurantId,restaurant), HttpStatus.OK);
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> addUser(@RequestBody User user) throws UserAlreadyExistsException {
        return new ResponseEntity<>(adminService.addUser(user), HttpStatus.OK);
    }

}
