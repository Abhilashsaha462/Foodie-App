package com.niit.authenticationservice.AuthenticationService.rabbitmq.Domain;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    @Id
    private String email;
    @Transient
    private String password;
    private String userName, userPhoneNo;
    private AddressDTO address;
    private List<RestaurantDTO> favorites;
}
