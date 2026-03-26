package com.amex.orders.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import com.amex.orders.dto.AddressInput;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	private String id;
	private String name;
	private AddressInput address; // embedded object no need of creating document
}