package com.AAGST.CustomerApp.Service;

import com.AAGST.CustomerApp.Entity.Transaction;
import com.AAGST.CustomerApp.utils.AggregateData;
import com.AAGST.CustomerApp.utils.SummaryData;
import com.AAGST.CustomerApp.utils.TransactionPerPage;
import com.AAGST.CustomerApp.utils.TransactionSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class TransactionService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public MatchOperation getMatchOperationObj(TransactionSender recieved){
        List<Criteria> criteriaList = new ArrayList<>();
        if(!recieved.getGender().equals("null")){
            criteriaList.add(Criteria.where("gender").is(recieved.getGender()));
        }
        if(!recieved.getCategory().equals("null")) {
            criteriaList.add(Criteria.where("category").is(recieved.getCategory()));
        }
        if(!recieved.getMerchant().equals("null")) {
            criteriaList.add(Criteria.where("merchant").is(recieved.getMerchant()));
        }
        if(!recieved.getCity().equals("null")) {
            criteriaList.add(Criteria.where("city").is(recieved.getCity()));
        }
        if(!recieved.getState().equals("null")) {
            criteriaList.add(Criteria.where("state").is(recieved.getState()));
        }
        if(!recieved.getProfession().equals("null")) {
            criteriaList.add(Criteria.where("Job").is(recieved.getProfession()));
        }

        if(recieved.getTransactionAmountUpper() >= 0 && recieved.getTransactionAmountLower() >= 0){
            criteriaList.add(Criteria.where("amt").gte(recieved.getTransactionAmountLower()).lte(recieved.getTransactionAmountUpper()));
        }
        MatchOperation match =new MatchOperation(!criteriaList.isEmpty()?new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()])):new Criteria());
        return match;
    }

    // used to generate query for getTransactions and getTransactionByPagination - both has same query
    public Query getQuery(Query query,TransactionSender recieved){
        Criteria a = new Criteria();
        if(!recieved.getGender().equals("null")){
            query.addCriteria(Criteria.where("gender").is(recieved.getGender()));
        }
        if(!recieved.getCategory().equals("null")) {
            query.addCriteria(Criteria.where("category").is(recieved.getCategory()));
        }
        if(!recieved.getMerchant().equals("null")) {
            query.addCriteria(Criteria.where("merchant").is(recieved.getMerchant()));
        }
        if(!recieved.getCity().equals("null")) {
            query.addCriteria(Criteria.where("city").is(recieved.getCity()));
        }
        if(!recieved.getState().equals("null")) {
            query.addCriteria(Criteria.where("state").is(recieved.getState()));
        }
        if(!recieved.getProfession().equals("null")) {
            query.addCriteria(Criteria.where("Job").is(recieved.getProfession()));
        }
        if(recieved.getTransactionAmountUpper() >= 0 && recieved.getTransactionAmountLower() >= 0){
            query.addCriteria(Criteria.where("amt").gte(recieved.getTransactionAmountLower()).lte(recieved.getTransactionAmountUpper()));
        }

        return query;
    }
    public List<Transaction> getTransactions(TransactionSender recieved){
        Query query = getQuery(new Query(),recieved);
        query.limit(20);
        List<Transaction> ret= this.mongoTemplate.find(query,Transaction.class);
        return ret;

    }
    public TransactionPerPage getTransactionByPagination(int pageNo,int size,TransactionSender recieved){
        // pageNo - tells the page no. we want
        // size - no. of docs per page
        Pageable pageable = PageRequest.of(pageNo,size);
        Query query = getQuery(new Query().with(pageable),recieved);

        Page<Transaction> page = PageableExecutionUtils.getPage(this.mongoTemplate.find(query,Transaction.class), pageable, () -> mongoTemplate.count(query, Transaction.class));

        int totalPages = page.getTotalPages();
        long totalElements = page.getTotalElements();
        int numOfElements = page.getNumberOfElements();
        int pageSize = page.getSize();

        TransactionPerPage response = new TransactionPerPage();
        response.setTransaction(page.getContent());
        response.setNumOfelements(numOfElements);
        response.setPageSize(pageSize);
        response.setTotalElements(totalElements);
        response.setTotalPages(totalPages);
        return response;

    }

    public SummaryData getSummary(TransactionSender recieved){
        System.out.println("hello --- ");
        SummaryData summaryData = new SummaryData();

        MatchOperation filterStates = this.getMatchOperationObj(recieved);

        GroupOperation groupByGender = group("gender").sum("amt").as("amount");
        GroupOperation groupByCategory = group("category").sum("amt").as("amount");
        GroupOperation groupByMerchant = group("merchant").sum("amt").as("amount");
        GroupOperation groupByCity = group("city").sum("amt").as("amount");
        GroupOperation groupByState = group("state").sum("amt").as("amount");
        GroupOperation groupByProfession = group("Job").sum("amt").as("amount");

        SortOperation sortDesc = sort(Sort.by(Sort.Direction.DESC,"amount"));
//        System.out.println(filterStates);
//        AggregationOperation limit = Aggregation.limit(20);

        List<AggregateData> result;
        Aggregation aggregation;
        AggregationResults<AggregateData> aggList;

        aggregation = newAggregation(filterStates,groupByGender,sortDesc);
        aggList =mongoTemplate.aggregate(aggregation, mongoTemplate.getCollectionName(Transaction.class), AggregateData.class);
        result = aggList.getMappedResults();
        summaryData.setGender(result);

        aggregation = newAggregation(filterStates,groupByCategory,sortDesc);
        aggList =mongoTemplate.aggregate(aggregation, mongoTemplate.getCollectionName(Transaction.class), AggregateData.class);
        result = aggList.getMappedResults();
        summaryData.setCategory(result);

        aggregation = newAggregation(filterStates,groupByMerchant,sortDesc);
        aggList =mongoTemplate.aggregate(aggregation, mongoTemplate.getCollectionName(Transaction.class), AggregateData.class);
        result = aggList.getMappedResults();
        summaryData.setMerchant(result);

        aggregation = newAggregation(filterStates,groupByCity,sortDesc);
        aggList =mongoTemplate.aggregate(aggregation, mongoTemplate.getCollectionName(Transaction.class), AggregateData.class);
        result = aggList.getMappedResults();
        summaryData.setCity(result);

        aggregation = newAggregation(filterStates,groupByState,sortDesc);
        aggList =mongoTemplate.aggregate(aggregation, mongoTemplate.getCollectionName(Transaction.class), AggregateData.class);
        result = aggList.getMappedResults();
        summaryData.setState(result);

        aggregation = newAggregation(filterStates,groupByProfession,sortDesc);
        aggList =mongoTemplate.aggregate(aggregation, mongoTemplate.getCollectionName(Transaction.class), AggregateData.class);
        result = aggList.getMappedResults();
        summaryData.setProfession(result);



//        System.out.println("---------------agg ----"+summaryData);
        return summaryData;
    }





}
