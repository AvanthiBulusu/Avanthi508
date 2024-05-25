package com.project.OnlinelearningPlatform.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.OnlinelearningPlatform.model.UserDetails;
import com.project.OnlinelearningPlatform.repository.UserRepo;
import com.project.OnlinelearningPlatform.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public List<UserDetails> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public Optional<UserDetails> getUserById(Long id) {
		return userRepo.findById(id);
	}

	@Override
	public void saveUser(UserDetails user) {
		userRepo.save(user);
	}

	@Override
	public void updateUserById(Long id, UserDetails user) {
		Optional<UserDetails> existingUser = userRepo.findById(id);
		if (existingUser.isPresent()) {
			UserDetails existingUseropt = existingUser.get();
			existingUseropt.setEmail(null);
			existingUseropt.setPassword(null);
			existingUseropt.setPhoneNumber(id);
			existingUseropt.setUserName(null);
		}
			
	}

	@Override
	public String deleteUserById(Long id) {
		Optional<UserDetails> user = userRepo.findById(id);
		if (user.isPresent()) {
			userRepo.deleteById(id);
			return "user has been deleted";
		} else
			return "User not found";
	}

}
