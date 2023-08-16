package com.AAGST.CustomerApp.ControllerTest;

import com.AAGST.CustomerApp.Entity.Transaction;
import com.AAGST.CustomerApp.utils.TransactionPerPage;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionRestControllerTest {
    @Autowired
    private TestRestTemplate template;

    @Test
    public void getTransactionTest() throws URISyntaxException{

        URI uri = new URI("http://localhost:8080/Transactions");


        ResponseEntity<List<Transaction>> response = template.exchange(uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Transaction>>() {
                });
        List<Transaction> responseBody = response.getBody();

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(20,responseBody.size());
    }
    @Test
    public void getTransactionPerPageResponseTest() throws URISyntaxException{

        URI uri = new URI("http://localhost:8080/Transactions/pages");


        ResponseEntity<TransactionPerPage> response = template.exchange(uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<TransactionPerPage>() {
                });
        TransactionPerPage responseBody = response.getBody();

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(1,responseBody.getTotalPages());
        assertEquals(10,responseBody.getTotalElements());
        assertEquals(10,responseBody.getNumOfelements());
        assertEquals(10,responseBody.getPageSize());

    }

    @Test
    public void getSummaryDataTest() throws URISyntaxException{

        URI uri = new URI("http://localhost:8080/Transactions/summary");
        ResponseEntity<TransactionPerPage> response = template.exchange(uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<TransactionPerPage>() {
                });
        TransactionPerPage responseBody = response.getBody();

        assertEquals(HttpStatus.OK,response.getStatusCode());

    }
}
