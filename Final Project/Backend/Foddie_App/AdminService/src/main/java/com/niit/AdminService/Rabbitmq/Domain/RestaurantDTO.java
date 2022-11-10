package com.niit.AdminService.Rabbitmq.Domain;

import com.niit.AdminService.domain.Menu;
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
    private byte[] url;
    private List<Menu> menuList;

}
