package com.amex.orders.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.amex.orders.dto.OrderInput;
import com.amex.orders.entity.Address;
import com.amex.orders.entity.Order;
import com.amex.orders.entity.User;
import com.amex.orders.service.OrderService;
import com.amex.orders.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderResolver {

	public final OrderService orderService;

	public final UserService userService;

	// Get order by orderId
	@QueryMapping
	public Order getOrderById(@Argument String id) {
		return orderService.getOrderById(id);
	}

	// Get orders by userId
	@QueryMapping
	public List<Order> getOrdersByUserId(@Argument String userId) {
		return orderService.getOrdersByUserId(userId);
	}

	// Get All orders
	@QueryMapping
	public List<Order> getAllOrders() {
		return orderService.getAllOrders();
	}

	// delete order by orderId
	@MutationMapping
	public String deleteOrderById(@Argument String id) {
		orderService.deleteOrder(id);
		return "Order deleted successfully";
	}

	// create order by orderId
	@MutationMapping
	public Order createOrder(@Argument String id, @Argument String userId, @Argument double amount) {
		Order order = new Order(id, userId, amount, "CREATED");
		return orderService.saveOrder(order);
	}

	@MutationMapping
	public Order createOrderWithUser(@Argument OrderInput input) {

		// ❗ Validate required fields
		if (input == null || input.getId() == null) {
			throw new IllegalArgumentException("Order ID cannot be null");
		}

		if (input.getUser() == null || input.getUser().getId() == null) {
			throw new IllegalArgumentException("User ID cannot be null");
		}

		// Optional address handling
		Address address = null;

		if (input.getUser().getAddress() != null) {
			address = new Address(input.getUser().getAddress().getStreet(), input.getUser().getAddress().getCity(),
					input.getUser().getAddress().getZip());
		}

		// Create user
		User user = new User(input.getUser().getId(), input.getUser().getName(), address);

		userService.save(user);

		// Optional fields with defaults
		double amount = input.getAmount() != null ? input.getAmount() : 0.0;
		String status = input.getStatus() != null ? input.getStatus() : "CREATED";

		Order order = new Order(input.getId(), user.getId(), amount, status);

		return orderService.saveOrder(order);
	}

	// update order by orderId
	@MutationMapping
	public Order updateOrderById(@Argument String id, @Argument double amount) {
		Order order = orderService.getOrderById(id);
		order.setAmount(amount);
		return orderService.saveOrder(order);
	}

	// ---------------- NESTED RESOLVER ----------------
	@SchemaMapping(typeName = "Order", field = "user")
	public User getUser(Order order) {
		return userService.findById(order.getUserId());
	}
}
