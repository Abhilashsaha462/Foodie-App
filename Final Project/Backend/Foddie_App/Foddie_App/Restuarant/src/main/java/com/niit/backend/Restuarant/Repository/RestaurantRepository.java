package com.niit.backend.Restuarant.Repository;

import com.niit.backend.Restuarant.Domain.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurant,Integer> {
    Restaurant findByRestName(String restName);
    Restaurant findByRestId(int restId);
}
