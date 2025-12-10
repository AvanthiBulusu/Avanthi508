package com.example.api.gateway.cb.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {

	@GetMapping("/online-learning-platform-fallback")
	public ResponseEntity<Map<String, String>> onlineLearningPlatformFallback() {
	    Map<String, String> response = new HashMap<>();
	    response.put("message", "The Online Learning Platform service is unavailable.");
	    response.put("timestamp", LocalDateTime.now().toString());
	    response.put("status", "503");
	    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
	}

	@GetMapping("/online-learning-platform-courses-fallback")
	public ResponseEntity<Map<String, String>> coursesFallback() {
	    Map<String, String> response = new HashMap<>();
	    response.put("message", "The Online Learning Platform Courses service is unavailable.");
	    response.put("timestamp", LocalDateTime.now().toString());
	    response.put("status", "503");
	    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
	}

	@GetMapping("/hystrix")
	public String dashboard() {
		return "forward:/hystrix"; // Redirect to Hystrix Dashboard
	}
}
