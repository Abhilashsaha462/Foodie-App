package com.niit.OrderServices.Repository;

import com.niit.OrderServices.Model.OrderDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderDetailsRepository extends MongoRepository<OrderDetails,Long> {

}
