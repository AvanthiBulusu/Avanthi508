package com.project.onlinelearningplatform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.onlinelearningplatform.model.User;

@Service
public interface UserService {

	public List<User> getAllUsers();

	public Optional<User> getUserById(Long id);

	public String deleteUserById(Long id);

	public void saveUser(User user);

	public void updateUserById(Long id, User user);

    User findByUsername(String username);

}
