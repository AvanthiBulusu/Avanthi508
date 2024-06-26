package com.project.onlinelearningplatform.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.onlinelearningplatform.model.AuthenticationResponse;
import com.project.onlinelearningplatform.service.impl.UserDetailsServiceImpl;
import com.project.onlinelearningplatform.utils.JWTUtil;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

	private AuthenticationManager authenticationManager;

	private JWTUtil jwtUtil;

	private UserDetailsServiceImpl userDetailsServiceImpl;

	public AuthenticationController(AuthenticationManager authenticationManager, JWTUtil jwtUtil,
			UserDetailsServiceImpl userDetailsServiceImpl) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	}
 
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestHeader("userName") String userName,
			@RequestHeader("Password") String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		} catch (AuthenticationException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userName);

		final String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
