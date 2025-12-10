package com.project.onlinelearningplatform.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.onlinelearningplatform.dto.UserDto;
import com.project.onlinelearningplatform.exception.UserAlreadyExistsException;
import com.project.onlinelearningplatform.exception.UserNotFoundException;
import com.project.onlinelearningplatform.mapper.mapperUser;
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
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		return Optional.ofNullable(userRepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User Not Found with the id : " + id)));
	}

	@Override
	public String deleteUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) {
			userRepo.deleteById(id);
			return "User has been deleted";
		} else {
			throw new UserNotFoundException("User Not Found with the id: " + id);
		}
	}

	@Override
	public void saveUser(UserDto userDto) throws UserAlreadyExistsException {
		User userEntity = mapperUser.maptoUser(new User(), userDto);
		Optional<User> userAlreadyExist = Optional.of(userRepo.findByUserName(userDto.getUserName()));
		if (userAlreadyExist != null)
			throw new UserAlreadyExistsException(
					"User Already exists cant create with this name : " + userEntity.getUserName());
		else
			userRepo.save(userEntity);
	}

	@Override
	public void updateUserById(Long id, UserDto userDto) throws UserNotFoundException {

		Optional<User> existingUser = Optional.of(userRepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User Not found to update with this id : " + id)));

		User existingUserMap = mapperUser.maptoUser(new User(), userDto);

		if (existingUser != null) {
			userRepo.save(existingUserMap);
		}
	}

	@Override
	public Optional<User> findByUsername(String userName) throws UserNotFoundException {
		return Optional.ofNullable(Optional.of(userRepo.findByUserName(userName))
				.orElseThrow(() -> new UserNotFoundException("User Not Found with this name : " + userName)));
	}

}
