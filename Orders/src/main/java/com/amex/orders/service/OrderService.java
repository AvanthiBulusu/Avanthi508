package com.amex.orders.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amex.orders.entity.Order;
import com.amex.orders.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;

	// create or update
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}

	// Get order by id
	public Order getOrderById(String id) {
		return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Oder not found"));
	}

	// GET all
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	// GET by userId
	public List<Order> getOrdersByUserId(String userId) {
		return orderRepository.findByUserId(userId);
	}

	// DELETE
	public void deleteOrder(String id) {
		orderRepository.deleteById(id);
	}
}
