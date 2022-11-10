package com.niit.OrderServices.Controller;

import UserDefinedException.OrderAlreadyExistsException;
import UserDefinedException.OrderNotFoundException;
import com.niit.OrderServices.Model.Order;
import com.niit.OrderServices.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order-services/")
public class OrderController {

    private ResponseEntity responseEntity;
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    //  generate An Order
    @PostMapping("order")
    public ResponseEntity<?> generateOrderCall(@RequestBody Order order) throws OrderAlreadyExistsException{
        try{
            orderService.generateOrder(order);
            responseEntity = new ResponseEntity(order, HttpStatus.CREATED);
        }catch (OrderAlreadyExistsException ex){
            throw ex;
        }catch (Exception e){
            responseEntity = new ResponseEntity("Error while generating order! Please Try again!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    //  delete Order
    @DeleteMapping("order/delete/{orderId}")
    public ResponseEntity<?> cancelOrderCall(@PathVariable String orderId) throws OrderNotFoundException{
        try{
            orderService.cancelOrder(orderId);
            responseEntity = new ResponseEntity("Order is Successfully canceled!", HttpStatus.OK);
        }catch (OrderNotFoundException ex){
            throw ex;
        }catch (Exception e){
            responseEntity = new ResponseEntity("Error while cancelling order! Please Try again!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    //  editing Order
    @PutMapping("order/update")
    public ResponseEntity<?> updateOrderCall(@RequestBody Order order) throws OrderNotFoundException{
        try{
            orderService.updateOrder(order);
            responseEntity = new ResponseEntity("Updated!", HttpStatus.OK);
        }catch (OrderNotFoundException ex){
            throw ex;
        }catch (Exception e){
            responseEntity = new ResponseEntity("Error while Updating! Please Try again!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}
