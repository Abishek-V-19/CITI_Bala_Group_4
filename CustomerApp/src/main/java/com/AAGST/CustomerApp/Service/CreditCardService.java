package com.AAGST.CustomerApp.Service;

import com.AAGST.CustomerApp.Entity.CreditCard;
import com.AAGST.CustomerApp.Entity.Customer;
import com.AAGST.CustomerApp.Exception.CardNotExistException;
import com.AAGST.CustomerApp.Exception.CustomerNotExistException;
import com.AAGST.CustomerApp.Repository.CreditCardRepository;
import com.AAGST.CustomerApp.Repository.CustomerRepository;
import com.AAGST.CustomerApp.utils.CreditCardAddSender;
import com.AAGST.CustomerApp.utils.CreditCardDeleteSender;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class CreditCardService {
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    private long getCreditCardCount(){
        return this.creditCardRepository.count();
    }
    private String curDateTime(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
    }
    public CreditCard addCreditCard(CreditCardAddSender recieved) throws CustomerNotExistException{
        // checking if customerId exist and if exist generate a creditcard
        Query query = new Query(Criteria.where("_id").is(recieved.getCustomerId()));

        if(!this.mongoTemplate.exists(query, Customer.class)){
            throw new CustomerNotExistException("Customer Doesnot exist - Please create customer");
        }
        CreditCard newCreditCard = new CreditCard();
        Date date = new Date();
        ObjectId cardNumber = new ObjectId(date, 100);
        newCreditCard.setCardNumber(cardNumber.toString());
        newCreditCard.setCreationTime(curDateTime());
        newCreditCard.setCustomerId(recieved.getCustomerId());
        newCreditCard.setStatus("Active");

        System.out.println(newCreditCard.toString());

        this.mongoTemplate.save(newCreditCard);
        return newCreditCard;
    }

    public void deleteCreditCard(CreditCardDeleteSender recieved) throws CardNotExistException {
        // checking if creditcard exist by cardnumber and customerId
        Query query = new Query(Criteria.where("_id").is(recieved.getCardNumber()));
        query.addCriteria(Criteria.where("customerId").is(recieved.getCustomerId()));

        if(!this.mongoTemplate.exists(query, CreditCard.class)){
            throw new CardNotExistException("Credit card details wrong - please enter a valid card number and corresponding customer number");
        }

        CreditCard found = mongoTemplate.findOne(query,CreditCard.class);
        mongoTemplate.remove(found);
        System.out.println(found.toString());

    }
    public void updateCreditCard(CreditCardDeleteSender recieved) throws CardNotExistException {
        // checking if creditcard exist by cardnumber and customerId
        Query query = new Query(Criteria.where("_id").is(recieved.getCardNumber()));
        query.addCriteria(Criteria.where("customerId").is(recieved.getCustomerId()));

        if(!this.mongoTemplate.exists(query, CreditCard.class)){
            throw new CardNotExistException("Credit card details wrong - please enter a valid card number and corresponding customer number");
        }

        CreditCard found = mongoTemplate.findOne(query,CreditCard.class);
        found.setStatus("Cancelled");
        mongoTemplate.save(found);
        System.out.println(found.toString());

    }

}
