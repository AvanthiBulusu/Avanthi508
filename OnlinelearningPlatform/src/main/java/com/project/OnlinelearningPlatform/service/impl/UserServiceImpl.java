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
	public void updateUserById(UserDetails user) {
        userRepo.save(user);		
	}

	@Override
	public void deleteUserById(Long id) {
		userRepo.deleteById(id);
	}

	
	
	
}
