package com.bankass.bankass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.application.Application;

@SpringBootApplication
public class BankassApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankassApplication.class, args);
		Application.launch(StockManagement.class, args);
	}

}
