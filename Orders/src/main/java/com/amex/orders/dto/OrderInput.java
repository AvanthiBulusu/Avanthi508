package com.amex.orders.dto;

import lombok.Data;

@Data
public class OrderInput {
	private String id;
	private String userId;
	private Double amount;
	private String status;
	private UserInput user;
}