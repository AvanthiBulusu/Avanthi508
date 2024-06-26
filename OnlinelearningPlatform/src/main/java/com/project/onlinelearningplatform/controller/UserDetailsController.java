package com.project.onlinelearningplatform.controller;

import java.util.List;
import java.util.Optional;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.onlinelearningplatform.model.User;
import com.project.onlinelearningplatform.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api")
public class UserDetailsController {

	@Autowired
	private UserService userService;

	@Operation(summary = "View a list of registered users")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
			@ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
			@ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found") })
	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllRegisteredUsers(@AuthenticationPrincipal UserDetails currentUserDetails) {
		if (currentUserDetails == null)
			return ResponseEntity.status(org.springframework.http.HttpStatus.UNAUTHORIZED).body("Unauthorized");

		List<User> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@GetMapping("getById/{id}")
	public Optional<User> getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	@Operation(summary = "Register a new User")
	@PostMapping("/register")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.SC_CREATED).body("user has been succesfully registered");

	}

	@Operation(summary = "Delete a user by ID")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id) {
		String result = userService.deleteUserById(id);
		if (result.contains("user has been deleted"))
			return ResponseEntity.status(org.springframework.http.HttpStatus.OK).body(result);
		else
			return ResponseEntity.status(org.springframework.http.HttpStatus.NOT_FOUND).body(result);

	}

	@Operation(summary = "update a user by ID")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUserById(@PathVariable("id") long id, @RequestBody User user) {
		if (userService.getUserById(id).isPresent()) {
			userService.updateUserById(id, user);
			return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED).body("user has been updated");
		} else
			return ResponseEntity.status(org.springframework.http.HttpStatus.NOT_FOUND).body("not found");
	}

}
