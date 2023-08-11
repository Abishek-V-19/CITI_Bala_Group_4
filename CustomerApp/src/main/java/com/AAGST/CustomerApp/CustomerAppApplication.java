package com.AAGST.CustomerApp;

import com.AAGST.CustomerApp.Service.TransactionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CustomerAppApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(CustomerAppApplication.class, args);
		TransactionService transactionService = context.getBean(TransactionService.class);
		System.out.println(transactionService.getTransactionCount());

	}

}
