package com.niit.backend.Restuarant.Service;

import com.niit.backend.Restuarant.Domain.Restaurant;
import com.niit.backend.Restuarant.Exception.RestaurantAlreadyExistException;
import com.niit.backend.Restuarant.Exception.RestaurantNotFoundException;

import java.util.List;

public interface RestaurantService {
    public Restaurant addRestaurant(Restaurant restaurant) throws RestaurantAlreadyExistException;
    public Restaurant findByName(String restName) throws RestaurantNotFoundException;
    public List<Restaurant>getAllRestaurants();
    Restaurant updateRestaurant(Restaurant restaurant)throws RestaurantNotFoundException;
}
