package com.niit.backend.Restuarant.Rabbitmq.Domain;

import com.niit.backend.Restuarant.Domain.Menu;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RestaurantDTO {
    private int restId;
    private String email;
    private String restName;
    private String city;
    private List<Menu> menuList;
    private byte[] url;
    }

