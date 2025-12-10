package com.project.onlinelearningplatform.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.project.onlinelearningplatform.*")
@RefreshScope
@EnableHystrix
public class OnlineLearningPlatformCoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineLearningPlatformCoursesApplication.class, args);
	}
}
