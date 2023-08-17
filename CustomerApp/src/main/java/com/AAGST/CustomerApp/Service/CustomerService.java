package com.AAGST.CustomerApp.Service;

import com.AAGST.CustomerApp.Entity.CreditCard;
import com.AAGST.CustomerApp.Entity.Customer;
import com.AAGST.CustomerApp.Entity.Transaction;
import com.AAGST.CustomerApp.Exception.CustomerNotExistException;
import com.AAGST.CustomerApp.Repository.CustomerRepository;
import com.AAGST.CustomerApp.utils.CustomerAddSender;
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

    public long getCount(){
        Query q = new Query();
        return mongoTemplate.count(q,Customer.class);
    }

    public Customer getCustomer(long customerId) throws CustomerNotExistException {
        Query query = new Query(Criteria.where("_id").is(customerId));
        if(!this.mongoTemplate.exists(query, Customer.class)){
            System.out.println("_-------Send data-------");
            throw new CustomerNotExistException("Customer Doesnot exist - Please create customer");
        }
        Customer ret = this.mongoTemplate.findOne(query,Customer.class);

        return ret;
    }

    public Customer addCustomer(CustomerAddSender recieved) {
        Customer newCustomer = new Customer();
        newCustomer.setCustomerId(this.getCount()+1);

        newCustomer.setGender(recieved.getGender());
        newCustomer.setDob(recieved.getDob());
        newCustomer.setJob(recieved.getProfession());
        newCustomer.setFirstName(recieved.getFirstName());
        newCustomer.setLastName(recieved.getLastName());
        return this.addCustomerWorker(newCustomer);
    }
    public Customer addCustomerWorker(Customer newCustomer){
        this.mongoTemplate.save(newCustomer);
        return newCustomer;
    }

}
