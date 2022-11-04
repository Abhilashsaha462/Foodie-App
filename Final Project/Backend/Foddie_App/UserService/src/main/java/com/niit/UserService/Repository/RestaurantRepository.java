package com.niit.UserService.Repository;

import com.niit.UserService.Model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, Integer> {

    Restaurant findByRestId(int id);
}
