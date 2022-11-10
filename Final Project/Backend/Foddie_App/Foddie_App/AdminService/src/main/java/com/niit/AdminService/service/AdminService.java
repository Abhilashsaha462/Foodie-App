package com.niit.AdminService.service;

import com.niit.AdminService.domain.Admin;
import com.niit.AdminService.domain.Restaurant;
import com.niit.AdminService.domain.User;
import com.niit.AdminService.exception.AdminAlreadyExistsException;
import com.niit.AdminService.exception.RestaurantAlreadyExistsException;
import com.niit.AdminService.exception.UserAlreadyExistsException;

public interface AdminService {

    public Admin registerUser(Admin admin) throws AdminAlreadyExistsException;

    public Restaurant addRestaurant(Restaurant restaurant) throws RestaurantAlreadyExistsException;

    public User addUser(User user) throws UserAlreadyExistsException;

    public Boolean removeRestaurant(String email);

    public Admin updateRestaurant(String restaurantId,Restaurant restaurant);

}
