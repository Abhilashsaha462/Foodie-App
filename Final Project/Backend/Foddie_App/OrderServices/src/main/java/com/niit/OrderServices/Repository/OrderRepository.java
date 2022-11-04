package com.niit.OrderServices.Repository;

import com.niit.OrderServices.Model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, Long> {

    Order findByOrderId(long id);
}
