//package com.project.onlinelearningplatform.courses.model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.AllArgsConstructor;
//
//@Entity
//@Table(name = "enrollments", uniqueConstraints = {@UniqueConstraint(columnNames = {"course_id", "user_id"})})
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Enrollment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "enrollment_id")
//    private Long enrollmentId;
//
//    @ManyToOne
//    @JoinColumn(name = "course_id", nullable = false)
//    private Course course;
//
//    @Column(name = "user_id", nullable = false)
//    private Long userId;
//}



