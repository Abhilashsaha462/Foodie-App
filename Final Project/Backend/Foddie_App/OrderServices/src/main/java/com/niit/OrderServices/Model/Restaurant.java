package com.niit.OrderServices.Model;

import org.springframework.data.annotation.Id;

public class Restaurant {
    @Id
    private int restId;
    private String restName, city;
}
