package com.AAGST.CustomerApp.ServiceTest;

import com.AAGST.CustomerApp.Entity.CreditCard;
import com.AAGST.CustomerApp.Entity.Customer;
import com.AAGST.CustomerApp.Service.CreditCardService;
import com.AAGST.CustomerApp.utils.CreditCardAddSender;
import com.AAGST.CustomerApp.utils.CreditCardDeleteSender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CreditCardServiceTest {
    // mocking the mongotemplate
    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private CreditCardService creditCardService;
    // when service object is created spring boot will inject the mock object of mongotemplate

    //arrange fixtures
    CreditCard c1,c2;
    CreditCardDeleteSender cds1;
    Query q1,q2;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
    }
    @BeforeEach
    public void setUp(){
        c1 = new CreditCard("12121",1,"15-08-2023","Active");
        c2 = new CreditCard("121232",10000,"15-08-2023","Active");

        cds1 = new CreditCardDeleteSender("12121",1);

        q1 = new Query(Criteria.where("_id").is(c1.getCustomerId()));
        q2 = new Query(Criteria.where("_id").is(cds1.getCardNumber()));
        q2.addCriteria(Criteria.where("customerId").is(cds1.getCustomerId()));
    }

    // act and assert
    @Test
    public void addCreditCardTestTrue(){
        // train mock object
        when(mongoTemplate.save(c1)).thenReturn(c1);
        when(mongoTemplate.exists(q1, Customer.class)).thenReturn(true);
        try {
            CreditCard returnObj = creditCardService.addCreditCardWorker(c1);
            assertEquals(c1.toString(),returnObj.toString());
        }
        catch(Exception e){
            assertEquals("Customer Doesnot exist - Please create customer",e.getMessage());
        }

    }

    @Test
    public void addCreditCardTestFalse(){
        // train mock object
        when(mongoTemplate.save(c2)).thenReturn(c2);
        when(mongoTemplate.exists(q1, Customer.class)).thenReturn(false);
        try {
            CreditCard returnObj = creditCardService.addCreditCardWorker(c2);
            assertEquals(c2.toString(),returnObj.toString());
        }
        catch(Exception e){
            assertEquals("Customer Doesnot exist - Please create customer",e.getMessage());
        }

    }

    @Test
    public void updateCreditCardTestTrue(){
        when(mongoTemplate.exists(q2, CreditCard.class)).thenReturn(true);
        when(mongoTemplate.findOne(q2,CreditCard.class)).thenReturn(c1);
        when(mongoTemplate.save(c1)).thenReturn(c1);
        try {
            CreditCard returnObj = creditCardService.updateCreditCard(cds1);
            assertEquals("Cancelled",returnObj.getStatus());
        }
        catch(Exception e){
            assertEquals("Credit card details wrong - please enter a valid card number and corresponding customer number",e.getMessage());
        }
    }

    @Test
    public void updateCreditCardTestFalse(){
        when(mongoTemplate.exists(q2, CreditCard.class)).thenReturn(false);
        when(mongoTemplate.findOne(q2,CreditCard.class)).thenReturn(c1);
        when(mongoTemplate.save(c1)).thenReturn(c1);
        try {
            CreditCard returnObj = creditCardService.updateCreditCard(cds1);
            assertEquals("Cancelled",returnObj.getStatus());
        }
        catch(Exception e){
            assertEquals("Credit card details wrong - please enter a valid card number and corresponding customer number",e.getMessage());
        }


    }

    @Test
    public void deleteCreditCardTestTrue(){
        when(mongoTemplate.exists(q2, CreditCard.class)).thenReturn(true);
        when(mongoTemplate.findOne(q2,CreditCard.class)).thenReturn(c1);
        when(mongoTemplate.save(c1)).thenReturn(c1);
        try {
            CreditCard returnObj = creditCardService.updateCreditCard(cds1);
            assertEquals(c1.toString(),returnObj.toString());
        }
        catch(Exception e){
            assertEquals("Credit card details wrong - please enter a valid card number and corresponding customer number",e.getMessage());
        }
    }

    @Test
    public void deleteCreditCardTestFalse(){
        when(mongoTemplate.exists(q2, CreditCard.class)).thenReturn(false);
        when(mongoTemplate.findOne(q2,CreditCard.class)).thenReturn(c1);
        when(mongoTemplate.save(c1)).thenReturn(c1);
        try {
            CreditCard returnObj = creditCardService.updateCreditCard(cds1);
            assertEquals(c1.toString(),returnObj.toString());
        }
        catch(Exception e){
            assertEquals("Credit card details wrong - please enter a valid card number and corresponding customer number",e.getMessage());
        }


    }

    @AfterEach
    public void destroy(){
        c1 = null;
        c2 = null;
        cds1 = null;
        q1 = null;
        q2 = null;
    }





}
