package com.project.onlinelearningplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.project.onlinelearningplatform") // Add the package where your service implementation is
@SpringBootApplication
public class OnlinelearningPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlinelearningPlatformApplication.class, args);
    }
}
