package com.AAGST.CustomerApp.Repository;

import com.AAGST.CustomerApp.Entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer,Long> {
}
