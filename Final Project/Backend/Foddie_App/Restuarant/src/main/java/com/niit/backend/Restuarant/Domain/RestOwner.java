package com.niit.backend.Restuarant.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class RestOwner {
    @Id
    private String email;
    private String password;
    private String ownerName;
    private String phoneNo;
    private String gstNo;
    private List<Address> address;

}
