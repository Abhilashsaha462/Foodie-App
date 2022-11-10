package com.niit.UserService.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
public class User {

    @Id
    private String email;
    @Transient
    private String password;

    private String userName, phoneNo;
    private List<Address> address;
    private List<Restaurant> favorites;
}
