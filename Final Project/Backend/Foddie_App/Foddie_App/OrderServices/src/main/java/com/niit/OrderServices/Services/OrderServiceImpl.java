package com.niit.OrderServices.Services;

import UserDefinedException.OrderAlreadyExistsException;
import UserDefinedException.OrderNotFoundException;
import com.niit.OrderServices.Model.Order;
import com.niit.OrderServices.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public Order generateOrder(Order order) throws OrderAlreadyExistsException {
        if(orderRepository.findById(order.getOrderId()).isPresent()){
            throw new OrderAlreadyExistsException();
        }
        return orderRepository.save(order);
    }

    @Override
    public boolean cancelOrder(String orderId) throws OrderNotFoundException {
        if(orderRepository.findById(orderId).isEmpty()){
            throw new OrderNotFoundException();
        }
        Order order = orderRepository.findByOrderId(orderId);
        orderRepository.delete(order);
        return true;
    }

    @Override
    public Order updateOrder(Order order) throws OrderNotFoundException {
        if(orderRepository.findById(order.getOrderId()).isEmpty()){
            throw new OrderNotFoundException();
        }
        return orderRepository.save(order);
    }
}
