package com.amex.notification.service.serviceImpl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.amex.notification.service.entity.Notification;
import com.amex.notification.service.repository.NotificationRepository;
import com.amex.notification.service.repository.OrderCreatedEventDto;

import jakarta.mail.internet.MimeMessage;

@Service
public class NotificationService {

	private final NotificationRepository repository;
	private final JavaMailSender emailSender;

	public NotificationService(NotificationRepository repository, JavaMailSender emailSender) {
		this.repository = repository;
		this.emailSender = emailSender;
	}

	@Value("${mail.from}")
	private String mailFrom;

	@Value("${mail.to}")
	private String mailTo;

	@Value("${mail.subject}")
	private String mailSubject;

	@Value("${mail.body.application}")
	private String application;

	@Value("${mail.body.environment}")
	private String environment;

	@Value("${mail.body.database}")
	private String database;

	public void processNotification(OrderCreatedEventDto event) {

		try {
			// Save notification
			Notification notification = new Notification("notification::" + event.getOrderId(), event.getUserId(),
					event.getOrderId(), "Order created successfully", "SENT");

			repository.save(notification);

			// Send email
			sendOrderNotification(event.getOrderId(), event.getUserId(), event.getAmount());

		} catch (Exception e) {

			// Save FAILED status
			Notification failed = new Notification("notification::" + event.getOrderId(), event.getUserId(),
					event.getOrderId(), "Order created failed", "FAILED");

			repository.save(failed);

			System.out.println("❌ Notification failed: " + e.getMessage());
		}
	}

	public void sendOrderNotification(String orderId, String userId, double amount) {

		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(mailFrom);

			String[] recipients = Arrays.stream(mailTo.split(",")).map(String::trim).toArray(String[]::new);

			helper.setTo(recipients);
			helper.setSubject(mailSubject);

			String bodyContent = buildOrderMailContent(orderId, userId, amount);
			helper.setText(bodyContent, true);

			emailSender.send(message);

		} catch (Exception e) {
		}
	}

	private String buildOrderMailContent(String orderId, String userId, double amount) {

		return "<html><body>" + "<h2>Order Confirmation ✅</h2>" + "<p>Your order has been successfully created.</p>" +

				"<h3>Order Details:</h3>" + "<ul>" + "<li><strong>Order ID:</strong> " + orderId + "</li>"
				+ "<li><strong>User ID:</strong> " + userId + "</li>" + "<li><strong>Amount:</strong> $" + amount
				+ "</li>" + "</ul>" +

				"<p>Thank you for shopping with us!</p>" + "</body></html>";
	}
}