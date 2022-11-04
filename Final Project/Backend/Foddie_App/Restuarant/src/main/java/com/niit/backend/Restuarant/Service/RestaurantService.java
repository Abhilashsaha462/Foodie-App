package com.niit.backend.Restuarant.Service;

import com.niit.backend.Restuarant.Domain.RestOwner;
import com.niit.backend.Restuarant.Domain.Restaurant;
import com.niit.backend.Restuarant.Exception.*;

import java.util.List;

public interface RestaurantService {
    public Restaurant addRestaurant(Restaurant restaurant,byte[] bytes) throws RestaurantAlreadyExistException;
    public Restaurant findByName(String restName) throws RestaurantNotFoundException;
    public List<Restaurant>getAllRestaurants();
    Restaurant updateRestaurant(Restaurant restaurant)throws RestaurantNotFoundException;
    List<Restaurant> getRestaurants(int[] restId)throws RestaurantsNotFoundException;
    public boolean deleteRestaurant(int restId)throws RestaurantNotFoundException;
    public RestOwner addOwner(RestOwner restOwner)throws OwnerAlreadyExistException;
    public RestOwner loginOwner(String email,String password)throws OwnerNotFoundException;
    List<Restaurant> getRestByEmail(String email)throws RestaurantsNotFoundException;
}
