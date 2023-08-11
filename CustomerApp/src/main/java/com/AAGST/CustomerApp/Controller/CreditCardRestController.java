package com.AAGST.CustomerApp.Controller;

import com.AAGST.CustomerApp.Entity.CreditCard;
import com.AAGST.CustomerApp.Entity.Transaction;
import com.AAGST.CustomerApp.Service.CreditCardService;
import com.AAGST.CustomerApp.utils.CreditCardAddSender;
import com.AAGST.CustomerApp.utils.CreditCardDeleteSender;
import com.AAGST.CustomerApp.utils.TransactionSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/CreditCard")
public class CreditCardRestController {
    @Autowired
    private CreditCardService creditCardService;

    @PostMapping("/add")
    public ResponseEntity<CreditCard> addCreditCard(@RequestBody CreditCardAddSender query)
    {   try {
        CreditCard newCreditCard = this.creditCardService.addCreditCard(query);
        return ResponseEntity.status(HttpStatus.OK).body(newCreditCard);
    }
    catch(Exception e){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
    }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteCreditCard(@RequestBody CreditCardDeleteSender query)
    {
        try {
            this.creditCardService.deleteCreditCard(query);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
    }


}
