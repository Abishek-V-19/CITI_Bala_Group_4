package com.AAGST.CustomerApp.Controller;

import com.AAGST.CustomerApp.Entity.Transaction;
import com.AAGST.CustomerApp.Service.TransactionService;
import com.AAGST.CustomerApp.utils.SummaryData;
import com.AAGST.CustomerApp.utils.TransactionPerPage;
import com.AAGST.CustomerApp.utils.TransactionSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final static Logger LOGGER  = LoggerFactory.getLogger(CreditCardRestController.class);
    @Autowired
    TransactionService transactionService;
    // http://localhost:8080
    // https://localhost:8080/swagger-ui/index.html


    @GetMapping
    public ResponseEntity<List<Transaction>> getTransaction(@RequestParam Map<String, String> params)
    {
        TransactionSender query = this.getTransactionSenderObj(params);
        List<Transaction> found = this.transactionService.getTransactions(query);
        this.transactionService.getSummary(query);
//        System.out.println(found.toString());
        return ResponseEntity.status(HttpStatus.OK).body(found);
    }

    @GetMapping("/pages")
    public ResponseEntity<TransactionPerPage>  getTransactionPerPageResponse(@RequestParam(required = false, defaultValue = "0") int pageno,
                                                                                   @RequestParam(required = false, defaultValue = "10") int size, @RequestParam Map<String, String> params)
    {
        TransactionSender query = this.getTransactionSenderObj(params);
       TransactionPerPage page = this.transactionService.getTransactionByPagination(pageno,size,query);
       paramsPrinter(params);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }
    @GetMapping("/summary")
    public ResponseEntity<SummaryData> getSummaryData(@RequestParam Map<String, String> params)
    {
        TransactionSender query = this.getTransactionSenderObj(params);
        SummaryData summaryData = this.transactionService.getSummary(query);

        return ResponseEntity.status(HttpStatus.OK).body(summaryData);
    }

    private TransactionSender getTransactionSenderObj(Map<String, String> params){
        paramsPrinter(params);
        TransactionSender query = new TransactionSender();

        for (String name: params.keySet()) {
            String key = name;
            if(key.equals("gender")){
                query.setGender(params.get("gender"));
            }
            else if(key.equals("category")){
                query.setCategory(params.get("category"));
            }
            else if(key.equals("merchant")){
                query.setMerchant(params.get("merchant"));
            }
            else if(key.equals("city")){
                query.setCity(params.get("city"));
            }
            else if(key.equals("state")){
                query.setState(params.get("state"));
            }
            else if(key.equals("transactionAmountLower")){
                query.setTransactionAmountLower(Long.parseLong(params.get("transactionAmountLower")==""?"-1":params.get("transactionAmountLower")));
            }
            else if(key.equals("transactionAmountUpper")){
                query.setTransactionAmountUpper(Long.parseLong(params.get("transactionAmountUpper")==""?"-1":params.get("transactionAmountUpper")));
            }
            else if(key.equals("profession")){
                query.setProfession(params.get("profession"));
            }
//            System.out.println(key + " " + value);
        }
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
