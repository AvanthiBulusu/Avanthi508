package com.project.onlinelearningplatform.courses.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.onlinelearningplatform.courses.model.Course;
import com.project.onlinelearningplatform.courses.model.Enrollment;
import com.project.onlinelearningplatform.courses.model.User;
import com.project.onlinelearningplatform.courses.repository.CourseRepository;
import com.project.onlinelearningplatform.courses.repository.EnrollmentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CourseService {

//	@Autowired
//	private CourseRepository courseRepository;
//
//	@Autowired
//	private RestTemplate restTemplate;
//
//	@Autowired
//	private EnrollmentRepository enrollmentRepository;

	private CourseRepository courseRepository;
	private RestTemplate restTemplate;
	private EnrollmentRepository enrollmentRepository;

//	private final String USER_SERVICE_URL = "http://localhost:8080/api/getById/";
	private static final String USER_SERVICE_URL = "http://localhost:9000/OnlineLearningPlatform/api/getById/";

	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	public Optional<Course> getCourseById(Long id) {
		return courseRepository.findById(id);
	}

	public String enrollInCourse(Long courseId, Long userId, String token) {
		// Check if the course exists
		Optional<Course> courseOpt = courseRepository.findById(courseId);
		if (courseOpt.isEmpty()) {
			return "Course not found.";
		}

		// Check if the user is already enrolled in the course
		Optional<Enrollment> existingEnrollment = enrollmentRepository.findByCourse_CourseIdAndUserId(courseId, userId);
		if (existingEnrollment.isPresent()) {
			return "User is already enrolled in this course.";
		}

		// Prepare headers with the token for authentication
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + token);

		// Create the request entity
		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

		// Fetch user details from the User Service
		ResponseEntity<User> responseEntity = restTemplate.exchange(USER_SERVICE_URL + userId, HttpMethod.GET,
				requestEntity, User.class);

		User user = responseEntity.getBody();

		if (user != null) {
			// Create and save the new enrollment
			Enrollment enrollment = new Enrollment();
			enrollment.setCourse(courseOpt.get());
			enrollment.setUserId(userId);
			enrollment.setUserName(user.getUserName());
			enrollmentRepository.save(enrollment);

			return "User " + user.getUserName() + " successfully enrolled in course " + courseOpt.get().getTitle()
					+ ".";
		} else {
			return "User not found.";
		}
	}

	public Course addCourse(Course course) {
		return courseRepository.save(course);
	}
}
