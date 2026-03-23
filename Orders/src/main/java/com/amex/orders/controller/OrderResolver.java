package com.amex.orders.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.amex.orders.entity.Order;
import com.amex.orders.service.OrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderResolver {

	public final OrderService orderService;

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

	// update order by orderId
	@MutationMapping
	public Order updateOrderById(@Argument String id, @Argument double amount) {
		Order order = orderService.getOrderById(id);
		order.setAmount(amount);
		return orderService.saveOrder(order);
	}
}
