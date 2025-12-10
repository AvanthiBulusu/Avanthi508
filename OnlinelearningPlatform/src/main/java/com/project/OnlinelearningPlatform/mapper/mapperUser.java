package com.project.onlinelearningplatform.mapper;

import com.project.onlinelearningplatform.dto.UserDto;
import com.project.onlinelearningplatform.model.User;

public class mapperUser {

	//we call data from table and set in dto from it we process anywhere else
	public static UserDto mapToUserDto(User user, UserDto userDto) {
		userDto.setUserName(user.getUserName());
		userDto.setEmail(user.getEmail());
		userDto.setPhoneNumber(user.getPhoneNumber());
		userDto.setPassword(user.getPassword());
		return userDto;
	}

	//inserting data in table this method is called
	public static User maptoUser(User user, UserDto userDto) {
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setPhoneNumber(user.getPhoneNumber());
		user.setPassword(userDto.getPassword());
		return user;
	}
}
