package com.AAGST.CustomerApp.Service;

import com.AAGST.CustomerApp.Entity.CreditCard;
import com.AAGST.CustomerApp.Entity.Customer;
import com.AAGST.CustomerApp.Exception.CardNotExistException;
import com.AAGST.CustomerApp.Exception.CustomerNotExistException;
import com.AAGST.CustomerApp.utils.CreditCardAddSender;
import com.AAGST.CustomerApp.utils.CreditCardDeleteSender;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class CreditCardService {
    @Autowired
    private MongoTemplate mongoTemplate;

    private final static Logger LOGGER  = LoggerFactory.getLogger(CreditCardService.class);


    private String curDateTime(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
    }
    public CreditCard addCreditCard(CreditCardAddSender recieved) throws CustomerNotExistException{
        LOGGER.debug("*************SERVICE - CreditCardService - FUNCTION NAME - addCreditCard()*************");
        // checking if customerId exist and if exist generate a creditcard
        CreditCard newCreditCard = new CreditCard();
        Date date = new Date();
        ObjectId cardNumber = new ObjectId(date, 100);
        newCreditCard.setCardNumber(cardNumber.toString());
        newCreditCard.setCreationTime(curDateTime());
        newCreditCard.setCustomerId(recieved.getCustomerId());
        newCreditCard.setStatus("Active");

        return addCreditCardWorker(newCreditCard,recieved.getFirstName(),recieved.getLastName());
    }
    public CreditCard addCreditCardWorker(CreditCard newCreditCard,String firstName,String lastName) throws CustomerNotExistException{
        Query query = new Query(Criteria.where("_id").is(newCreditCard.getCustomerId()));
        query.addCriteria(Criteria.where("first").is(firstName));
        query.addCriteria(Criteria.where("last").is(lastName));

        if(!this.mongoTemplate.exists(query, Customer.class)){
            throw new CustomerNotExistException("Customer Doesnot exist - Please create customer");
        }
//        System.out.println(newCreditCard.toString());
        this.mongoTemplate.save(newCreditCard);
        return newCreditCard;
    }

    public CreditCard deleteCreditCard(CreditCardDeleteSender recieved) throws CardNotExistException {
        LOGGER.debug("*************SERVICE - CreditCardService - FUNCTION NAME - deleteCreditCard()*************");
        // checking if creditcard exist by cardnumber and customerId
        Query query1 = new Query(Criteria.where("_id").is(recieved.getCardNumber()));
        query1.addCriteria(Criteria.where("customerId").is(recieved.getCustomerId()));


        if(!this.mongoTemplate.exists(query1, CreditCard.class)){
            throw new CardNotExistException("Credit card details wrong - please enter a valid card number and corresponding customer number");
        }

        CreditCard found = mongoTemplate.findOne(query1,CreditCard.class);
        mongoTemplate.remove(found);
//        System.out.println(found.toString());
        return found;
    }
    public CreditCard updateCreditCard(CreditCardDeleteSender recieved) throws CardNotExistException {
        LOGGER.debug("*************SERVICE - CreditCardService - FUNCTION NAME - updateCreditCard()*************");
        // checking if creditcard exist by cardnumber and customerId
        Query query1 = new Query(Criteria.where("_id").is(recieved.getCardNumber()));
        query1.addCriteria(Criteria.where("customerId").is(recieved.getCustomerId()));


        if(!this.mongoTemplate.exists(query1, CreditCard.class)){
            throw new CardNotExistException("Credit card details wrong - please enter a valid card number and corresponding customer number");
        }

        CreditCard found = mongoTemplate.findOne(query1,CreditCard.class);
        found.setStatus("Cancelled");
        mongoTemplate.save(found);
//        System.out.println(found.toString());
        return found;

    }

    public List<CreditCard> getCreditCardDetails(long customerId){
        Query query = new Query(Criteria.where("customerId").is(customerId));
        query.addCriteria(Criteria.where("status").is("Active"));

        List<CreditCard> found = mongoTemplate.find(query,CreditCard.class);
        return found;
    }

}