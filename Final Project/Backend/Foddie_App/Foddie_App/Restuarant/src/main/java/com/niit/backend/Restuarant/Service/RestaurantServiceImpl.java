package com.niit.backend.Restuarant.Service;

import com.niit.backend.Restuarant.Domain.Restaurant;
import com.niit.backend.Restuarant.Exception.RestaurantAlreadyExistException;
import com.niit.backend.Restuarant.Exception.RestaurantNotFoundException;
import com.niit.backend.Restuarant.Repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService{
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }
    @Override
    public Restaurant addRestaurant(Restaurant restaurant) throws RestaurantAlreadyExistException {
        if (restaurantRepository.findById(restaurant.getRestId()).isPresent()){
            throw new RestaurantAlreadyExistException();
        }else {
            restaurantRepository.save(restaurant);
            System.out.println("Restaurant Saved Successfully...!!!");
        }
        return restaurant;
    }

    @Override
    public Restaurant findByName(String restName)throws RestaurantNotFoundException {
        Restaurant restaurant = restaurantRepository.findByRestName(restName);
        if (restaurant.getRestName().equals(restName)){
            return restaurant;
        }else {
            throw new RestaurantNotFoundException();
        }
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantNotFoundException {
        if (restaurantRepository.findById(restaurant.getRestId()).isEmpty()){
            throw new RestaurantNotFoundException();
        }
       return restaurantRepository.save(restaurant);
    }
}
