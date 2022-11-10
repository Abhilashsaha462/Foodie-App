package com.niit.AdminService.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String email;
    @Transient
    private String password;
    private String userName;
    private String phoneNo;
    private List<Address> addresses;
}
