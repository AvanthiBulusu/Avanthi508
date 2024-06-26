package com.project.onlinelearningplatform.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.onlinelearningplatform.model.User;
import com.project.onlinelearningplatform.repository.UserRepo;
import com.project.onlinelearningplatform.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public Optional<User> getUserById(Long id) {
		return userRepo.findById(id);
	}

	@Override
	public String deleteUserById(Long id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) {
			userRepo.deleteById(id);
			return "User has been deleted";
		} else {
			return "User not found";
		}
	}

	@Override
	public void saveUser(User user) {
		userRepo.save(user);
	}

	@Override
	public void updateUserById(Long id, User user) {
		Optional<User> existingUser = userRepo.findById(id);
		if (existingUser.isPresent()) {
			User existingUseropt = existingUser.get();
			// Update user fields as needed
		}
	}

	@Override
	public User findByUsername(String userName) {
		return userRepo.findByUserName(userName);
	}

}
