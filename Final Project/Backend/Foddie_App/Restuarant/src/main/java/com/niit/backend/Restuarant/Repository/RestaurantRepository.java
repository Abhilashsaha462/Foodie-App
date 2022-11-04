package com.niit.backend.Restuarant.Repository;

import com.niit.backend.Restuarant.Domain.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RestaurantRepository extends MongoRepository<Restaurant,Integer> {
    List<Restaurant> findByRestNameContaining(String restName);
    Restaurant findByRestId(int restId);
    Restaurant findByRestName(String restName);
    Restaurant findByEmail(String email);
    Restaurant findByCity(String city);
    List<Restaurant> findByEmailContaining(String email);
}
