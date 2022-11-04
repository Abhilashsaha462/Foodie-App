package com.niit.authenticationservice.AuthenticationService.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@ToString
@AllArgsConstructor
@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {
    @Id
    private String email;
    private String password;

}
