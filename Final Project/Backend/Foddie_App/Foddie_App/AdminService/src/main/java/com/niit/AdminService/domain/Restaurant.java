package com.niit.AdminService.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Restaurant {

    @Id
    private String restaurantId;
    private String restaurantName;
    private String location;
    private String email;
//    private List<User> favFood;

}
