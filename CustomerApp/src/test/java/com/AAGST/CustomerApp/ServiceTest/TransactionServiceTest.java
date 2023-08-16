package com.AAGST.CustomerApp.ServiceTest;

import com.AAGST.CustomerApp.Entity.CreditCard;
import com.AAGST.CustomerApp.Entity.Transaction;
import com.AAGST.CustomerApp.Service.CreditCardService;
import com.AAGST.CustomerApp.Service.TransactionService;
import com.AAGST.CustomerApp.utils.CreditCardDeleteSender;
import com.AAGST.CustomerApp.utils.TransactionPerPage;
import com.AAGST.CustomerApp.utils.TransactionSender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransactionServiceTest {
    // mocking the mongotemplate
    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private TransactionService transactionService;
    // when service object is created spring boot will inject the mock object of mongotemplate

    //arrange fixtures
    List<Transaction> collection;
    Transaction t1,t2,t3;
    TransactionSender ts1;
    Query q1,q2;
    int pageNo,size;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
    }
    @BeforeEach
    public void setUp(){
        t1 = new Transaction();
        t2 = new Transaction();
        t3 = new Transaction();

        collection = Arrays.asList(t1,t2,t3);

        ts1 = new TransactionSender();
        ts1.setGender("M");
        ts1.setState("OK");

        pageNo = 0;
        size = 5;

        Pageable pageable = PageRequest.of(pageNo,size);

        q1 = transactionService.getQuery(new Query(),ts1);
        q1.limit(20);
        q2 = transactionService.getQuery(new Query().with(pageable),ts1);


    }


    @Test
    public void getTransactionsTest(){
        when(mongoTemplate.find(q1,Transaction.class)).thenReturn(collection);

        List<Transaction> returnList = transactionService.getTransactions(ts1);

        assertEquals(collection.size(),returnList.size());
        assertEquals(collection,returnList);
    }

    @Test
    public void getTransactionByPaginationTest(){
        System.out.println(collection);
        System.out.println(collection.size());
        when(mongoTemplate.find(q2,Transaction.class)).thenReturn(collection);
        when(mongoTemplate.count(q2, Transaction.class)).thenReturn((long) collection.size());

        TransactionPerPage returnObj = transactionService.getTransactionByPagination(pageNo,size,ts1);

        assertEquals(1,returnObj.getTotalPages());
        assertEquals(3,returnObj.getTotalElements());
        assertEquals(3,returnObj.getNumOfelements());
        assertEquals(5,returnObj.getPageSize());

    }

    @AfterEach
    public void destroy(){
        t1 = null;
        t2 = null;
        t3 = null;
        collection = null;
        ts1 = null;
        q1 = null;
        q2 = null;
    }

}
