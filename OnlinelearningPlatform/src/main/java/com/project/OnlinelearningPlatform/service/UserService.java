package com.project.OnlinelearningPlatform.service;

import java.util.List;
import java.util.Optional;

import com.project.OnlinelearningPlatform.model.UserDetails;

public interface UserService {

	public List<UserDetails> getAllUsers();
	
	public Optional<UserDetails> getUserById(Long id);
			
	public void deleteUserById(Long id);

	public void saveUser(UserDetails user);

	void updateUserById(UserDetails user);
}
