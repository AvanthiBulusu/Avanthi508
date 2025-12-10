package com.project.onlinelearningplatform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.onlinelearningplatform.dto.UserDto;
import com.project.onlinelearningplatform.exception.UserAlreadyExistsException;
import com.project.onlinelearningplatform.exception.UserNotFoundException;
import com.project.onlinelearningplatform.model.User;

@Service
public interface UserService {

	public List<User> getAllUsers();

	public Optional<User> getUserById(Long id) throws UserNotFoundException;

	public String deleteUserById(Long id) throws UserNotFoundException;

	public void saveUser(UserDto userDto) throws UserAlreadyExistsException;

	public void updateUserById(Long id, UserDto userDto) throws UserNotFoundException;

	public Optional<User> findByUsername(String username) throws UserNotFoundException;

}
