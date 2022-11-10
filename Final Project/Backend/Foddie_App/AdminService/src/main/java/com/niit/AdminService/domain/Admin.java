package com.niit.AdminService.domain;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Admin {

    @Id
    private String email;
    private String password;
    private String userName;
    private String phoneNo;
    private List<Address> address;
}
