package com.amex.orders.dto;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInput {

	@Id
	private String id;
	private String name;
	private AddressInput address;
}