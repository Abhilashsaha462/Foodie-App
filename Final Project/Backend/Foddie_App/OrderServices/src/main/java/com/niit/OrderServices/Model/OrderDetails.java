package com.niit.OrderServices.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document
public class OrderDetails {
    @Id
    private String orderId;
    private String amount;
    private String receipt;
    private String status;
    private String email;
    private String paymentId;
}
