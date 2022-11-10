package com.niit.backend.Restuarant.Consumer;
import com.niit.backend.Restuarant.Domain.Restaurant;
import com.niit.backend.Restuarant.Exception.RestaurantAlreadyExistException;
import com.niit.backend.Restuarant.Rabbitmq.Domain.RestaurantDTO;
import com.niit.backend.Restuarant.Service.RestaurantServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    private RestaurantServiceImpl restaurantService;
    @RabbitListener(queues = "rest_queue")
    public void getDataFromRabbitMq(RestaurantDTO restDTO) throws RestaurantAlreadyExistException {
        Restaurant rest =new Restaurant();
        rest.setRestId(restDTO.getRestId());
        rest.setRestName(restDTO.getRestName());
        rest.setCity(restDTO.getCity());
        rest.setMenuList(restDTO.getMenuList());
        System.out.println("Trying to Save Data into MySQL..." +rest.getRestName());
        restaurantService.addRestaurant(rest,restDTO.getUrl());
    }
}

