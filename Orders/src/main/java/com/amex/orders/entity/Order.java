package com.amex.orders.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	@Id
	private String id;
	private String userId;
	private double amount;
	private String status;

}
