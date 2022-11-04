package com.niit.backend.Restuarant.Repository;

import com.niit.backend.Restuarant.Domain.RestOwner;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends MongoRepository<RestOwner,String> {
    RestOwner findByEmailAndPassword(String email,String password);
}
