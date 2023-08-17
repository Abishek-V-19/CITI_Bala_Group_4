package com.AAGST.CustomerApp.Controller;

import com.AAGST.CustomerApp.Entity.CreditCard;
import com.AAGST.CustomerApp.Entity.Transaction;
import com.AAGST.CustomerApp.Service.CreditCardService;
import com.AAGST.CustomerApp.utils.CreditCardAddSender;
import com.AAGST.CustomerApp.utils.CreditCardDeleteSender;
import com.AAGST.CustomerApp.utils.CreditCardDetailsSender;
import com.AAGST.CustomerApp.utils.TransactionSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/CreditCard")
public class CreditCardRestController {
    private final static Logger LOGGER  = LoggerFactory.getLogger(CreditCardRestController.class);

    @Autowired
    private CreditCardService creditCardService;

    @PostMapping("/add")
    public ResponseEntity<CreditCard> addCreditCard(@RequestBody CreditCardAddSender query)
    {   try {
        LOGGER.debug("*************POST METHOD - FUNCTION NAME - addCreditCard()*************");
        System.out.println("cut id ------- "+query.getCustomerId());
        CreditCard newCreditCard = this.creditCardService.addCreditCard(query);
        return ResponseEntity.status(HttpStatus.OK).body(newCreditCard);
    }
    catch(Exception e){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
    }
    }

    //    @PostMapping("/delete")
//    public ResponseEntity<String> deleteCreditCard(@RequestBody CreditCardDeleteSender query)
//    {
//        try {
//            this.creditCardService.deleteCreditCard(query);
//            return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
//        }
//        catch(Exception e){
//            return ResponseEntity.status(HttpStatus.OK).body("Delete was'nt Successfully");
//        }
//    }
    @PutMapping("/delete/{cardNumber}/{customerId}")
    public ResponseEntity<String> updateCreditCardStatus(@PathVariable String cardNumber,@PathVariable long customerId)
    {
        try {
            LOGGER.debug("*************PUT METHOD - FUNCTION NAME - updateCreditCardStatus()*************");
            CreditCardDeleteSender query = new CreditCardDeleteSender(cardNumber,customerId);
            this.creditCardService.updateCreditCard(query);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
        }
        catch(Exception e){
//            System.out.println(HttpStatus.NOT_ACCEPTABLE);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Delete Not successful");
        }
    }
    @DeleteMapping("/delete/{cardNumber}/{customerId}")
    public ResponseEntity<String> deleteCreditCard(@PathVariable String cardNumber,@PathVariable long customerId)
    {
        try {
            LOGGER.debug("*************PUT METHOD - FUNCTION NAME - deleteCreditCard()*************");
            CreditCardDeleteSender query = new CreditCardDeleteSender(cardNumber,customerId);
            this.creditCardService.deleteCreditCard(query);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());

        }
    }

    @GetMapping
    public ResponseEntity<CreditCardDetailsSender> getCreditCards(@RequestParam long customerId){
        LOGGER.debug("*************GET METHOD - FUNCTION NAME - getCreditCards()*************");
        CreditCardDetailsSender found = this.creditCardService.getCreditCardDetails(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(found);
    }


}