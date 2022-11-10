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
    private int restId;
    private String email;
    private String restName;
    private String city;
    private byte[] url;
    private List<Menu>menuList;

}
