package com.AAGST.CustomerApp.Service;

import com.AAGST.CustomerApp.Entity.Customer;
import com.AAGST.CustomerApp.Entity.Transaction;
import com.AAGST.CustomerApp.Exception.CustomerNotExistException;
import com.AAGST.CustomerApp.Repository.CustomerRepository;
import com.AAGST.CustomerApp.utils.TransactionSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Customer getCustomer(long customerId) throws CustomerNotExistException {
        Query query = new Query(Criteria.where("_id").is(customerId));
        if(!this.mongoTemplate.exists(query, Customer.class)){
            System.out.println("_-------Send data-------");
            throw new CustomerNotExistException("Customer Doesnot exist - Please create customer");
        }
        Customer ret = this.mongoTemplate.findOne(query,Customer.class);

        return ret;
    }

}
