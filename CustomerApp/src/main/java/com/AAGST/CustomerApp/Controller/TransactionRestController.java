package com.AAGST.CustomerApp.Controller;

import com.AAGST.CustomerApp.Entity.Transaction;
import com.AAGST.CustomerApp.utils.TransactionPerPage;
import com.AAGST.CustomerApp.utils.TransactionSender;
import com.AAGST.CustomerApp.Service.TransactionService;
import com.fasterxml.jackson.databind.util.TypeKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/Transactions")
public class TransactionRestController {
    @Autowired
    TransactionService transactionService;
    // http://localhost:8080
    // https://localhost:8080/swagger-ui/index.html

    @GetMapping
    public ResponseEntity<List<Transaction>> getTransaction(@RequestParam Map<String, String> params)
    {
        TransactionSender query = this.getTransactionSenderObj(params);
        List<Transaction> found = this.transactionService.getTransactions(query);
        System.out.println(found.toString());
        return ResponseEntity.status(HttpStatus.OK).body(found);
    }

    @GetMapping("/pages")
    public ResponseEntity<TransactionPerPage>  addTransactionPerPageResponse(@RequestParam(required = false, defaultValue = "0") int pageno,
                                                                                   @RequestParam(required = false, defaultValue = "10") int size, @RequestParam Map<String, String> params)
    {
        TransactionSender query = this.getTransactionSenderObj(params);
       TransactionPerPage p = this.transactionService.getTransactionByPagination(pageno,size,query);
        return ResponseEntity.status(HttpStatus.OK).body(p);
    }

    private TransactionSender getTransactionSenderObj(Map<String, String> params){
        paramsPrinter(params);
        TransactionSender query = new TransactionSender();
        query.setGender(params.get("gender"));
        query.setCategory(params.get("category"));
        query.setMerchant(params.get("merchant"));
        query.setCity(params.get("city"));
        query.setState(params.get("state"));
        query.setTransactionAmountLower(Long.parseLong(params.get("transactionAmountLower")));
        query.setTransactionAmountUpper(Long.parseLong(params.get("transactionAmountUpper")));
        query.setProfession(params.get("profession"));
        return query;
    }
    private void paramsPrinter(Map<String, String> params){
        for (String name: params.keySet()) {
            String key = name;
            String value = params.get(key);
            System.out.println(key + " " + value);
        }
    }

}
