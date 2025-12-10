package com.project.onlinelearningplatform.courses.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.onlinelearningplatform.courses.model.Course;
import com.project.onlinelearningplatform.courses.service.impl.CourseService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@GetMapping("/getAllCourses")
	public List<Course> getAllCourses() {
		return courseService.getAllCourses();
	}

	@GetMapping("/{id}")
	public Optional<Course> getCourseById(@PathVariable Long id) {
		return courseService.getCourseById(id);
	}

	@PostMapping("/addCourse")
	public ResponseEntity<Course> addCourse(@RequestBody Course course) {
		Course newCourse = courseService.addCourse(course);
		return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
	}

	@PostMapping("/{courseId}/enroll/{userId}")
	public ResponseEntity<String> enrollInCourse(@PathVariable Long courseId, @PathVariable Long userId,
			HttpServletRequest request) {

		// Extract the Authorization header
		String authorizationHeader = request.getHeader("Authorization");

		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			return ResponseEntity.status(401).body("Unauthorized: Bearer token missing or malformed");
		}

		// Extract the JWT token from the header
		String token = authorizationHeader.substring(7);

		// Pass the token to the service layer for further processing
		String response = courseService.enrollInCourse(courseId, userId, token);
		return ResponseEntity.ok(response);
	}
}
