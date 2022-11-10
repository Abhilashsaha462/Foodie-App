package com.niit.AdminService.Producer;

import com.niit.AdminService.Rabbitmq.Domain.RestaurantDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private RabbitTemplate rabbitTemplate;
    private DirectExchange directExchange;
    @Autowired
    Producer(RabbitTemplate rabbitTemplate,DirectExchange directExchange){
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }
    public void sendMessageToRabbitmq(RestaurantDTO restDTO){
        rabbitTemplate.convertAndSend(directExchange.getName(),"rest_routing",restDTO);
    }
}
