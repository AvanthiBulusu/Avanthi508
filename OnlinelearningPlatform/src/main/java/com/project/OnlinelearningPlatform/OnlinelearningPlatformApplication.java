package com.project.onlinelearningplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.project.onlinelearningplatform")
@ComponentScan("com.project.onlinelearningplatform.*")
@RefreshScope
@EnableHystrix
public class OnlinelearningPlatformApplication {

	public static void main(String[] args) {
		//System.setProperty("spring.config.import", "optional:configserver:http://onlinelearning-configserver:8888/");
		ApplicationContext context = SpringApplication.run(OnlinelearningPlatformApplication.class, args);

		// Print all bean names created by Spring Boot
//		Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
	}
}
