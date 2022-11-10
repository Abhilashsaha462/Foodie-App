package com.niit.UserService.Services;

import UserDefinedException.RestaurantNotFoundException;
import UserDefinedException.UserAlreadyExistsException;
import UserDefinedException.UserNotFoundException;
import com.niit.UserService.Model.Restaurant;
import com.niit.UserService.Model.User;

import java.util.List;

public interface UserService {

    User register(User user) throws UserAlreadyExistsException;
    User addToFavorite(Restaurant restaurant, String email) throws UserNotFoundException;
    User removeFromFavorite(int id, String email) throws RestaurantNotFoundException, UserNotFoundException;
    User updateUserDetails(User user) throws UserNotFoundException;
    List<Restaurant> getFavorite(String email) throws UserNotFoundException;
}
