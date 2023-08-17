package com.AAGST.CustomerApp.ServiceTest;

import com.AAGST.CustomerApp.Entity.CreditCard;
import com.AAGST.CustomerApp.Entity.Customer;
import com.AAGST.CustomerApp.Service.CustomerService;
import com.AAGST.CustomerApp.utils.CreditCardDeleteSender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceTest {

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    CustomerService customerService;

    Query q1,q2;

    Customer cust1;
    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
    }
    @BeforeEach
    public void setUp(){
        cust1 = new Customer();
        cust1.setCustomerId(1);

        q1 = new Query(Criteria.where("_id").is(cust1.getCustomerId()));
        q2 = new Query();
    }

    @Test
    public void getCountTest(){
        when(mongoTemplate.count(q2,Customer.class)).thenReturn(3L);

        long resp = this.customerService.getCount();

        assertEquals(3L,resp);
    }

    @Test
    public void getCustomerTestTrue(){
        when(mongoTemplate.exists(q1, Customer.class)).thenReturn(true);
        when(mongoTemplate.findOne(q1,Customer.class)).thenReturn(cust1);

        try {
            Customer resp = this.customerService.getCustomer(cust1.getCustomerId());
            assertEquals(cust1.getCustomerId(),resp.getCustomerId());
        }
        catch(Exception e){
            assertEquals("Customer Doesnot exist - Please create customer",e.getMessage());
        }

    }

    @Test
    public void getCustomerTestFalse(){
        when(mongoTemplate.exists(q1, Customer.class)).thenReturn(false);
        when(mongoTemplate.findOne(q1,Customer.class)).thenReturn(cust1);

        try {
            Customer resp = this.customerService.getCustomer(cust1.getCustomerId());
            assertEquals(cust1.getCustomerId(),resp.getCustomerId());
        }
        catch(Exception e){
            assertEquals("Customer Doesnot exist - Please create customer",e.getMessage());
        }

    }

    @Test
    public void addCustomerWorkerTest(){
        when(this.mongoTemplate.save(cust1)).thenReturn(cust1);

        Customer resp = this.customerService.addCustomerWorker(cust1);

        assertEquals(cust1.getCustomerId(),resp.getCustomerId());
    }

    @AfterEach
    public void destroy(){
        cust1 = null;
        q1 = null;
        q2 = null;
    }
}
