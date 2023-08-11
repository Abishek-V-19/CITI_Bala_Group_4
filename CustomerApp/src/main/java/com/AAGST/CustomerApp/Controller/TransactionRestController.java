package com.AAGST.CustomerApp.Controller;

import com.AAGST.CustomerApp.Entity.Transaction;
import com.AAGST.CustomerApp.utils.TransactionPerPage;
import com.AAGST.CustomerApp.utils.TransactionSender;
import com.AAGST.CustomerApp.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/Transactions")
public class TransactionRestController {
    @Autowired
    TransactionService transactionService;
 // http://localhost:8080
    // https://localhost:8080/swagger-ui/index.html
    @GetMapping
    public String greet(){
        return "SUCCESS";
    }
    @PostMapping
    public ResponseEntity<List<Transaction>> getTransaction(@RequestBody TransactionSender query)
    {
        List<Transaction> found = this.transactionService.getTransactions(query);
//        System.out.println(found.toString());
        return ResponseEntity.status(HttpStatus.OK).body(found);
    }

    @PostMapping("/pages")
    public ResponseEntity<TransactionPerPage>  addTransactionPerPageResponse(@RequestParam(required = false, defaultValue = "0") int pageno,
                                                                                   @RequestParam(required = false, defaultValue = "10") int size, @RequestBody TransactionSender query)
    {
       TransactionPerPage p = this.transactionService.getTransactionByPagination(pageno,size,query);
        return ResponseEntity.status(HttpStatus.OK).body(p);
    }

}
