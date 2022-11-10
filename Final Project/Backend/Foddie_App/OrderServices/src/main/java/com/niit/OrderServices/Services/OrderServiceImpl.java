package com.niit.OrderServices.Services;

import UserDefinedException.OrderAlreadyExistsException;
import UserDefinedException.OrderNotFoundException;
import com.niit.OrderServices.Model.Bill;
import com.niit.OrderServices.Model.Menu;
import com.niit.OrderServices.Model.Order;
import com.niit.OrderServices.Repository.OrderRepository;
import com.niit.OrderServices.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    Random rand=new Random();

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,UserRepository userRepository){
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Order generateOrder(Order order) throws OrderAlreadyExistsException {
        if(orderRepository.findById(order.getOrderId()).isPresent()){
            throw new OrderAlreadyExistsException();
        }
        return orderRepository.save(order);
    }

    @Override
    public boolean cancelOrder(long orderId) throws OrderNotFoundException {
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
    @Override
    public Order placeOrder(List<Menu> menuList,String email) throws OrderNotFoundException {
        if (userRepository.findByEmail(email).getEmail()==null){
            throw new OrderNotFoundException();

        }else {
            Order order = new Order();
            Bill bill = new Bill();
            order.setOrderId((rand.nextInt(1000000) + 1000000000l) * (rand.nextInt(900) + 100));
            order.setUser(userRepository.findByEmail(email));
            bill.setBillId((rand.nextInt(1000000) + 1000000000l) * (rand.nextInt(900) + 100));
            double cost = menuList.stream().map(Menu::getPrice).reduce((x, y)->x+y).get();
            bill.setTotalPrice(cost+(cost*0.18)-(cost*0.05));
            bill.setMenuList(menuList);
            order.setBill(bill);
            orderRepository.save(order);
            return order;

        }
    }

    @Override
    public Order getOrder(long orderId) throws OrderNotFoundException {
        Order order = orderRepository.findByOrderId(orderId);
        if (order.getOrderId()==orderId){
            System.out.println(orderId);
            System.out.println("Inside Order, "+order);
            return order;
        }else {
            throw new OrderNotFoundException();
        }
    }
}
