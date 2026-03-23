package com.amex.notification.service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.amex.notification.service.repository.OrderCreatedEventDto;
import com.amex.notification.service.serviceImpl.NotificationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderEventConsumer {

	private final NotificationService notificationService;

	@KafkaListener(topics = "order-events", groupId = "notification-group")
	public void consume(OrderCreatedEventDto event) {

		System.out.println("Received event: " + event);

		notificationService.processNotification(event);
	}
}