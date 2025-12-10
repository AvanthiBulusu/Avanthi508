package com.project.onlinelearningplatform.service.impl;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.onlinelearningplatform.repository.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final PasswordEncoder passwordEncoder;

	private final UserRepo userRepository;

	public UserDetailsServiceImpl(PasswordEncoder passwordEncoder, UserRepo userRepository) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		com.project.onlinelearningplatform.model.User user = userRepository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new User(user.getUserName(), passwordEncoder.encode(user.getPassword()), new ArrayList<>());

	}
}
