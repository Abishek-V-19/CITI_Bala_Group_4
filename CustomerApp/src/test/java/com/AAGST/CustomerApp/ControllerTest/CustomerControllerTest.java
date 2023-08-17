package com.AAGST.CustomerApp.ControllerTest;

import com.AAGST.CustomerApp.Entity.CreditCard;
import com.AAGST.CustomerApp.Entity.Customer;
import com.AAGST.CustomerApp.Entity.Transaction;
import com.AAGST.CustomerApp.utils.CreditCardAddSender;
import com.AAGST.CustomerApp.utils.CreditCardDetailsSender;
import com.AAGST.CustomerApp.utils.CustomerAddSender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {
    @Autowired
    TestRestTemplate template;

    private CustomerAddSender c1;
    @BeforeEach
    public void setUp(){
        c1 = new CustomerAddSender("Ramya","ST","F","tailor","12/02/2003");
    }
    @Test
    public void getDetailsTest() throws URISyntaxException{

        URI uri = new URI("http://localhost:8080/customer?customerId=1");


        ResponseEntity<Customer> response = template.exchange(uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Customer>() {
                });
        Customer responseBody = response.getBody();

        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void addCreditCardTest() throws URISyntaxException{
        URI uri = new URI("http://localhost:8080/customer/add");
        ResponseEntity<Customer> response = template.postForEntity(uri,c1,Customer.class);
        Customer newCustomer = response.getBody();

        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @AfterEach
    public void destroy(){
        c1 = null;
    }





}
