package com.project.onlinelearningplatform.controller;

import java.util.List;
import java.util.Optional;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.onlinelearningplatform.dto.UserDto;
import com.project.onlinelearningplatform.exception.UserAlreadyExistsException;
import com.project.onlinelearningplatform.exception.UserNotFoundException;
import com.project.onlinelearningplatform.model.User;
import com.project.onlinelearningplatform.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "CRUD REST API for OnlineLearningPlatform", description = "CRUD REST API in OnlineLearningPlatform to CREATE, UPDATE, FETCH AND DELETE User/Customer details")
@RestController
@RequestMapping("/api")
@Validated
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

	@Operation(summary = "View a registered user by id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
			@ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
			@ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found") })
	@GetMapping("getById/{id}")
	public Optional<User> getUserById(@PathVariable Long id) throws UserNotFoundException {
		return userService.getUserById(id);
	}

	@Operation(summary = "Register a new User")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Successfully Register user"),
			@ApiResponse(responseCode = "401", description = "You are not authorized to Register the resource"),
			@ApiResponse(responseCode = "403", description = "Register the resource you were trying to reach is forbidden")})
	@PostMapping("/register")
	public ResponseEntity<String> saveUser(@Valid @RequestBody UserDto userDto) throws UserAlreadyExistsException {
		userService.saveUser(userDto);
		return ResponseEntity.status(HttpStatus.SC_CREATED).body("user has been succesfully registered");
	}

	@Operation(summary = "Delete a user by ID")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id) throws UserNotFoundException {
		String result = userService.deleteUserById(id);
		if (result.contains("user has been deleted"))
			return ResponseEntity.status(org.springframework.http.HttpStatus.OK).body(result);
		else
			return ResponseEntity.status(org.springframework.http.HttpStatus.NOT_FOUND).body(result);

	}

	@Operation(summary = "update a user by ID")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUserById(@Valid @PathVariable("id") long id, @RequestBody UserDto userDto)
			throws UserNotFoundException {
		userService.updateUserById(id, userDto);
		return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED).body("user has been updated");
	}

}
