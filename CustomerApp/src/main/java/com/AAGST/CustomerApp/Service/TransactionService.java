package com.AAGST.CustomerApp.Service;

import com.AAGST.CustomerApp.Entity.Transaction;
import com.AAGST.CustomerApp.utils.TransactionPerPage;
import com.AAGST.CustomerApp.utils.TransactionSender;
import com.AAGST.CustomerApp.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    public long getTransactionCount(){
        return this.transactionRepository.count();
    }

    // used to generate query for getTransactions and getTransactionByPagination - both has same query
    private Query getQuery(Query query,TransactionSender recieved){
        if(!recieved.getGender().equals("null")){
            System.out.println("asasas"+recieved.getGender());
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
            System.out.println("asasas"+recieved.getGender());
            query.addCriteria(Criteria.where("state").is(recieved.getState()));
        }
        if(!recieved.getProfession().equals("null")) {
            query.addCriteria(Criteria.where("Job").is(recieved.getProfession()));
        }
        if(recieved.getTransactionAmountUpper() >= 0 && recieved.getTransactionAmountLower() >= 0 && recieved.getTransactionAmountLower() <= recieved.getTransactionAmountUpper()){
            query.addCriteria(Criteria.where("amt").gte(recieved.getTransactionAmountLower()).lte(recieved.getTransactionAmountUpper()));
        }

        return query;
    }
    public List<Transaction> getTransactions(TransactionSender recieved){
        Query query = getQuery(new Query(),recieved);
//        System.out.println(query.toString( ));
        List<Transaction> ret= this.mongoTemplate.find(query,Transaction.class);
//        return mongoTemplate.findOne(query, Transaction.class);
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





}
