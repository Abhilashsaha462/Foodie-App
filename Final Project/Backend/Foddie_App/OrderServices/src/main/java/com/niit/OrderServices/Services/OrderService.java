package com.niit.OrderServices.Services;

import UserDefinedException.OrderAlreadyExistsException;
import UserDefinedException.OrderNotFoundException;
import com.niit.OrderServices.Model.Menu;
import com.niit.OrderServices.Model.Order;

import java.util.List;

public interface OrderService {

    Order generateOrder(Order order) throws OrderAlreadyExistsException;
    boolean cancelOrder(long orderId) throws OrderNotFoundException;
    Order updateOrder(Order order) throws OrderNotFoundException;
    public Order placeOrder(List<Menu> menuList, String email) throws OrderNotFoundException;
    public Order getOrder(long orderId)throws OrderNotFoundException;

}
