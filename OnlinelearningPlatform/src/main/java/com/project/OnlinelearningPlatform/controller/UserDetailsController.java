package com.project.OnlinelearningPlatform.controller;

import java.util.List;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.OnlinelearningPlatform.model.UserDetails;
import com.project.OnlinelearningPlatform.service.UserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api")
@Api(value = "ExampleController", description = "REST APIs related to Example Entity")
public class UserDetailsController {

	@Autowired
    private UserService userService; 
	
	@GetMapping("/getAllUsers")	
	public ResponseEntity<List<UserDetails>> getAllRegisteredUsers(){
	  List<UserDetails> users = userService.getAllUsers();
		return ResponseEntity.status(HttpStatus.SC_OK).body(users);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> saveUser(@RequestBody UserDetails user){
		    userService.saveUser(user);		
		    return ResponseEntity.status(HttpStatus.SC_CREATED).body("user has been succesfully registered");
		
	}
	
}
