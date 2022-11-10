package com.niit.UserService.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Document
public class User {

    @Id
    private String email;
    @Transient
    private String password;

    private String userName, userPhoneNo;
    private Address address;
    private List<Restaurant> favorites;
}
