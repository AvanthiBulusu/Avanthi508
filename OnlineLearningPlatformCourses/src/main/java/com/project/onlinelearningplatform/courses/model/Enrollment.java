package com.project.onlinelearningplatform.courses.model;
//

//import com.project.onlinelearningplatform.model.User;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.Data;
//
//@Entity
//@Data
//@Table(name = "enrollments")
//public class Enrollment {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long enrollmentId;
//
//	@ManyToOne
//	private Course course;
//
//	@ManyToOne
//	private User user;
//}

//import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
@Table(name = "enrollments", uniqueConstraints = @UniqueConstraint(columnNames = { "course_id", "user_id" }))
public class Enrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enrollment_id")
	private Long enrollmentId;

	@Column(name = "userId")
	private Long userId;
	
	@Column(name = "userName")
	private String userName;

	@ManyToOne
	@JoinColumn(name = "course_id", foreignKey = @ForeignKey(name = "fk_course"))
	private Course course;

}
