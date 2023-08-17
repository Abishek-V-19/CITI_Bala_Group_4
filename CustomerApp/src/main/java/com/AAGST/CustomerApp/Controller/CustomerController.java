package com.AAGST.CustomerApp.Controller;

import com.AAGST.CustomerApp.Entity.CreditCard;
import com.AAGST.CustomerApp.Entity.Customer;
import com.AAGST.CustomerApp.Entity.Transaction;
import com.AAGST.CustomerApp.Service.CustomerService;
import com.AAGST.CustomerApp.utils.CreditCardAddSender;
import com.AAGST.CustomerApp.utils.CustomerAddSender;
import com.AAGST.CustomerApp.utils.TransactionSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @GetMapping
    public ResponseEntity<Customer> getDetails(@RequestParam long customerId)
    {
        try {
            Customer found = this.customerService.getCustomer(customerId);
            System.out.println(found);
            return ResponseEntity.status(HttpStatus.OK).body(found);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Customer> addCreditCard(@RequestBody CustomerAddSender recieved)
    {
        Customer created = this.customerService.addCustomer(recieved);
        return ResponseEntity.status(HttpStatus.OK).body(created);

    }
}
