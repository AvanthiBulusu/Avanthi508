-- Create Course database and use it
CREATE DATABASE IF NOT EXISTS Course;
USE Course;

-- Create course_table
CREATE TABLE IF NOT EXISTS course_table (
  course_id BIGINT NOT NULL AUTO_INCREMENT,
  description VARCHAR(255),
  instructor VARCHAR(255),
  title VARCHAR(255),
  PRIMARY KEY (course_id)
);

-- Insert course data
INSERT INTO course_table (description, instructor, title) VALUES
('A comprehensive course on Java programming.', 'John Doe', 'Introduction to Java'),
('A comprehensive course on Python programming.', 'John', 'Introduction to Python'),
('A comprehensive course on C++ programming.', 'John', 'Introduction to C++');

-- Create course_enrolled_user_ids
CREATE TABLE IF NOT EXISTS course_enrolled_user_ids (
  course_course_id BIGINT NOT NULL,
  enrolled_user_ids BIGINT DEFAULT NULL,
  FOREIGN KEY (course_course_id) REFERENCES course_table (course_id)
);

-- Create enrollments table
CREATE TABLE IF NOT EXISTS enrollments (
  enrollment_id BIGINT NOT NULL AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  course_id BIGINT NOT NULL,
  user_name VARCHAR(255),
  PRIMARY KEY (enrollment_id),
  UNIQUE KEY unique_course_user (course_id, user_id),
  FOREIGN KEY (course_id) REFERENCES course_table(course_id)
);

-- Create user_details table
CREATE TABLE IF NOT EXISTS user_details (
  user_id BIGINT NOT NULL,
  email VARCHAR(255),
  password VARCHAR(255),
  phone_number BIGINT,
  user_name VARCHAR(255),
  PRIMARY KEY (user_id)
);

-- Create user_details_seq table
CREATE TABLE IF NOT EXISTS user_details_seq (
  next_val BIGINT
);

INSERT INTO user_details_seq VALUES (1);