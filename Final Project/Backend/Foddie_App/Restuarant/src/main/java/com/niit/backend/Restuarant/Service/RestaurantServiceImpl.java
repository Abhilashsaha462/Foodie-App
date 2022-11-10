package com.niit.backend.Restuarant.Service;

import com.niit.backend.Restuarant.Domain.RestOwner;
import com.niit.backend.Restuarant.Domain.Restaurant;
import com.niit.backend.Restuarant.Exception.*;
import com.niit.backend.Restuarant.Repository.OwnerRepository;
import com.niit.backend.Restuarant.Repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService{
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }
    @Override
    public Restaurant addRestaurant(Restaurant restaurant,byte[] bytes) throws RestaurantAlreadyExistException {
        if (restaurantRepository.findById(restaurant.getRestId()).isPresent()){
            throw new RestaurantAlreadyExistException();
        }else {
           // RestOwner restOwner = ownerRepository.findById(restaurant.getEmail()).get();
                restaurant.setUrl(bytes);
                restaurantRepository.save(restaurant);
                System.out.println("Restaurant Saved Successfully...!!!");
            }

        return restaurant;
    }

    @Override
    public Restaurant findByName(String restName)throws RestaurantNotFoundException {
        System.out.println("Service"+restName);
        Restaurant restaurant = restaurantRepository.findByRestName(restName);
        System.out.println("restaurant"+restaurant);
        if (restaurant.getRestName().equals(restName)){
            System.out.println("Reached...");
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
        if (restaurantRepository.findById(restaurant.getRestId()).isEmpty()) {
            throw new RestaurantNotFoundException();
        } else {
//            System.out.println("Here");
//            Restaurant restaurant1 = restaurantRepository.findById(restaurant.getRestId()).get();
//            System.out.println(1);
//            restaurant1.setRestId(restaurant.getRestId());
//            restaurant1.setEmail(restaurant.getEmail());
//            restaurant1.setRestName(restaurant.getRestName());
//            restaurant1.setCity(restaurant.getCity());
//            System.out.println(2);

//            System.out.println(3);
//            restaurant1.setMenuList(restaurant.getMenuList());
//            System.out.println(restaurant1);
            Restaurant restaurant1 = restaurantRepository.findById(restaurant.getRestId()).get();
            if (restaurant.getUrl()==null){
                restaurant.setUrl(restaurant1.getUrl());
            }
            return restaurantRepository.save(restaurant);
        }
    }

    @Override
    public List<Restaurant> getRestaurants(int[] restId) throws RestaurantsNotFoundException{
        System.out.println("Ok");
        List<Restaurant> list = new ArrayList<>();
        Arrays.stream(restId).forEach(x->{
            Restaurant restaurant = restaurantRepository.findById(x).get();
            list.add(restaurant);
        });
        return list;
    }

    @Override
    public boolean deleteRestaurant(int restId) throws RestaurantNotFoundException {
        Restaurant restaurant = new Restaurant();
        if (restaurantRepository.findById(restId).isEmpty()){
            throw new RestaurantNotFoundException();
        }else {
            restaurant = restaurantRepository.findById(restId).get();
            restaurantRepository.delete(restaurant);
            return true;
        }
    }

    @Override
    public RestOwner addOwner(RestOwner restOwner) throws OwnerAlreadyExistException {
        if (ownerRepository.findById(restOwner.getEmail()).isPresent()){
            throw new OwnerAlreadyExistException();
        }else {
            ownerRepository.save(restOwner);
            return restOwner;
        }
    }

    @Override
    public RestOwner loginOwner(String email, String password) throws OwnerNotFoundException {
       RestOwner restOwner = ownerRepository.findByEmailAndPassword(email,password);
       if (restOwner==null){
           throw new OwnerNotFoundException();
       }else {
           return restOwner;
       }
    }

    @Override
    public List<Restaurant> getRestByEmail(String email) throws RestaurantsNotFoundException {
    List<Restaurant> list = new ArrayList<>();
        list = restaurantRepository.findByEmailContaining(email);

   return list;

    }
}
