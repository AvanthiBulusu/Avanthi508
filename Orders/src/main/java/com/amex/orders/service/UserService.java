package com.amex.orders.service;

import org.springframework.stereotype.Service;

import com.amex.orders.entity.User;
import com.amex.orders.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public User save(User user) {
		return userRepository.save(user);
	}

	public User getById(String id) {
	    return userRepository.findById(id).orElse(null);
	}
}