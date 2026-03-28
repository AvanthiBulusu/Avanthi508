package com.amex.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.amex.orders")
public class OrdersApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrdersApplication.class, args);
	}
}