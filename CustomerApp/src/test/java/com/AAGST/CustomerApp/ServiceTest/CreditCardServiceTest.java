package com.AAGST.CustomerApp.ServiceTest;

import com.AAGST.CustomerApp.Entity.CreditCard;
import com.AAGST.CustomerApp.Entity.Customer;
import com.AAGST.CustomerApp.Service.CreditCardService;
import com.AAGST.CustomerApp.utils.CreditCardAddSender;
import com.AAGST.CustomerApp.utils.CreditCardDeleteSender;
import com.AAGST.CustomerApp.utils.CreditCardDetailsSender;
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

import java.util.Arrays;
import java.util.List;

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
    CreditCard c1,c2,c3;
    Customer cust1;
    CreditCardDeleteSender cds1;
    Query q1,q2,q3;
    String firstName,lastName;

    List<CreditCard> lcc;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
    }
    @BeforeEach
    public void setUp(){
        c1 = new CreditCard("12121",1,"15-08-2023","Active","type");
        c2 = new CreditCard("121232",10000,"15-08-2023","Active","type");
        c3 = new CreditCard("121asa21",1,"15-08-2023","Active","type");

        cust1 = new Customer();
        cust1.setCustomerId(1);

        lcc = Arrays.asList(c1,c2);

        cds1 = new CreditCardDeleteSender("12121",1);

        q1 = new Query(Criteria.where("_id").is(c1.getCustomerId()));
        q2 = new Query(Criteria.where("_id").is(cds1.getCardNumber()));
        q2.addCriteria(Criteria.where("customerId").is(cds1.getCustomerId()));
        q3 = new Query(Criteria.where("customerId").is(c1.getCustomerId()));
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
            CreditCard returnObj = creditCardService.addCreditCardWorker(c1);
            assertEquals(c2.toString(),returnObj.toString());
        }
        catch(Exception e){
            assertEquals("Customer Doesnot exist - Please create customer",e.getMessage());
        }

    }

    @Test
    public void updateCreditCardTestTrue(){
        when(mongoTemplate.exists(q2, CreditCard.class)).thenReturn(true);
        when(mongoTemplate.exists(q1, Customer.class)).thenReturn(true);
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
        when(mongoTemplate.exists(q1, Customer.class)).thenReturn(false);
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
        when(mongoTemplate.exists(q1, Customer.class)).thenReturn(true);
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
        when(mongoTemplate.exists(q1, Customer.class)).thenReturn(false);
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
    public void getCreditCardDetailsTest(){
        when(mongoTemplate.find(q3,CreditCard.class)).thenReturn(lcc);
        when(mongoTemplate.findOne(q1,Customer.class)).thenReturn(cust1);

        CreditCardDetailsSender resp = this.creditCardService.getCreditCardDetails(cust1.getCustomerId());

        assertEquals(cust1.getCustomerId(),resp.getCustomer().getCustomerId());
        assertEquals(lcc,resp.getCreditCards());
    }

    @AfterEach
    public void destroy(){
        c1 = null;
        c2 = null;
        c3 = null;
        cds1 = null;
        cust1 = null;
        lcc = null;
        q1 = null;
        q2 = null;
        q3 = null;
    }





}
