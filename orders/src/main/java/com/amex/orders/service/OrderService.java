package com.amex.orders.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Service;

import com.amex.orders.order.entity.Order;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	@Qualifier("orderTemplate")
	private final CouchbaseTemplate orderTemplate;

	// SAVE
	public Order saveOrder(Order order) {
		return orderTemplate.save(order);
	}

	// GET BY ID
	public Order getOrderById(String id) {
		return orderTemplate.findById(Order.class).one(id);
	}

	// GET ALL
	public List<Order> getAllOrders() {
		return orderTemplate.findByQuery(Order.class).all();
	}

	// GET BY USER ID
	public List<Order> getOrdersByUserId(String userId) {
		return orderTemplate.findByQuery(Order.class)
				.matching(org.springframework.data.couchbase.core.query.QueryCriteria.where("userId").is(userId)).all();
	}

	// DELETE
	public void deleteOrder(String id) {
		orderTemplate.removeById(Order.class).one(id);
	}
}