package com.AAGST.CustomerApp.ServiceTest;

import com.AAGST.CustomerApp.Entity.CreditCard;
import com.AAGST.CustomerApp.Entity.Transaction;
import com.AAGST.CustomerApp.Service.CreditCardService;
import com.AAGST.CustomerApp.Service.TransactionService;
import com.AAGST.CustomerApp.utils.*;
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
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

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

    List<AggregateData> lad1;
    AggregateData ad1,ad2;

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

        ad1 = new AggregateData("key1",123232.9);
        ad2 = new AggregateData("key2",122.9);
        lad1 = Arrays.asList(ad1,ad1);

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

    @Test
    public void getSummaryTest(){
        MatchOperation filterStates = transactionService.getMatchOperationObj(ts1);
        GroupOperation groupByGender = group("gender").sum("amt").as("amount");
        GroupOperation groupByCategory = group("category").sum("amt").as("amount");
        GroupOperation groupByMerchant = group("merchant").sum("amt").as("amount");
        GroupOperation groupByCity = group("city").sum("amt").as("amount");
        GroupOperation groupByState = group("state").sum("amt").as("amount");
        GroupOperation groupByProfession = group("Job").sum("amt").as("amount");



        when(mongoTemplate.getCollectionName(Transaction.class)).thenReturn("transaction");
        when(mongoTemplate.aggregate(newAggregation(filterStates,groupByGender), "transaction", AggregateData.class).getMappedResults()).thenReturn(lad1);
        when(mongoTemplate.aggregate(newAggregation(filterStates,groupByCategory), "transaction", AggregateData.class).getMappedResults()).thenReturn(lad1);
        when(mongoTemplate.aggregate(newAggregation(filterStates,groupByMerchant), "transaction", AggregateData.class).getMappedResults()).thenReturn(lad1);
        when(mongoTemplate.aggregate(newAggregation(filterStates,groupByCity), "transaction", AggregateData.class).getMappedResults()).thenReturn(lad1);
        when(mongoTemplate.aggregate(newAggregation(filterStates,groupByState), "transaction", AggregateData.class).getMappedResults()).thenReturn(lad1);
        when(mongoTemplate.aggregate(newAggregation(filterStates,groupByProfession), "transaction", AggregateData.class).getMappedResults()).thenReturn(lad1);

        SummaryData summaryData = transactionService.getSummary(ts1);

        assertEquals(lad1.toString(),summaryData.getGender().toString());
//        assertEquals(lad1,summaryData.getCategory());
//        assertEquals(lad1,summaryData.getCity());
//        assertEquals(lad1,summaryData.getMerchant());
//        assertEquals(lad1,summaryData.getProfession());
//        assertEquals(lad1,summaryData.getState());
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
