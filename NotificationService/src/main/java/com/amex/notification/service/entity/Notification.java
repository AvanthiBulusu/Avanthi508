package com.amex.notification.service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Notification {

	@Id
	private String id;

	private String userId;
	private String orderId;
	private String message;
	private String status; // SENT / FAILED
}
