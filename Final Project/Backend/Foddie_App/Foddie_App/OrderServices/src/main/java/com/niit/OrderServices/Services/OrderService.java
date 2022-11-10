package com.niit.OrderServices.Services;

import UserDefinedException.OrderAlreadyExistsException;
import UserDefinedException.OrderNotFoundException;
import com.niit.OrderServices.Model.Order;

public interface OrderService {

    Order generateOrder(Order order) throws OrderAlreadyExistsException;
    boolean cancelOrder(String orderId) throws OrderNotFoundException;
    Order updateOrder(Order order) throws OrderNotFoundException;

}
