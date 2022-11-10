package com.niit.AdminService.repository;

import com.niit.AdminService.domain.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurant,Integer> {

}
