package com.jsp.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
@EnableScheduling
public class ECommerceCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceCloneApplication.class, args);
	}

}
