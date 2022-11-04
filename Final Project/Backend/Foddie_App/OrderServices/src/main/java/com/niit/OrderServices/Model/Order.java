package com.niit.OrderServices.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Document
public class Order {

    @Id
    private long orderId;
    private User user;
    private Bill bill;
}
