package com.niit.backend.Restuarant.Domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
