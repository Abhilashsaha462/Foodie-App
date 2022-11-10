package com.niit.authenticationservice.AuthenticationService.rabbitmq.Domain;

import org.springframework.data.annotation.Id;

public class RestaurantDTO {
    @Id
    private int restId;
    private String restName, city;
}
