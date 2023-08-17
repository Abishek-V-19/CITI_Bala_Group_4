package com.AAGST.CustomerApp;

import com.AAGST.CustomerApp.Service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CustomerAppApplication {
	private static final Logger LOGGER= LoggerFactory.getLogger(CustomerAppApplication.class);
	public static void main(String[] args) {
		LOGGER.info("APPLICATION UP AND RUNNING");
		ConfigurableApplicationContext context = SpringApplication.run(CustomerAppApplication.class, args);

	}

}
