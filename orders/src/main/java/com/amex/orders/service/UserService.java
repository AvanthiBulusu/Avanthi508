package com.amex.orders.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Service;

import com.amex.orders.order.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	@Qualifier("userTemplate")
	private final CouchbaseTemplate userTemplate;

	public User save(User user) {
		return userTemplate.save(user);
	}

	public User findById(String id) {
		return userTemplate.findById(User.class).one(id);
	}
}