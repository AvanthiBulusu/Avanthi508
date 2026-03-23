package com.amex.notification.service.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEventDto {

	private String orderId;
	private String userId;
	private double amount;
	private String status;
}