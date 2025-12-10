package com.example.api.gateway.cb.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				// Route for ONLINELEARNINGPLATFORM service with Resilience4j
				.route("online-learning-platform",
						r -> r.path("/platform/**").filters(
								f -> f.rewritePath("/platform/(?<segment>.*)", "/OnlineLearningPlatform/api/${segment}")
										.circuitBreaker(cb -> cb.setName("onlineLearningPlatformCB")
												.setFallbackUri("forward:/online-learning-platform-fallback")))
								.uri("lb://ONLINELEARNINGPLATFORM"))
				// Route for ONLINELEARNINGPLATFORMCOURSES service with Resilience4j
				.route("online-learning-platform-courses", r -> r.path("/courses/**").filters(f -> f
						.rewritePath("/courses/(?<segment>.*)", "/OnlineLearningPlatformCourses/courses/${segment}")
						.circuitBreaker(cb -> cb.setName("onlineLearningPlatformCoursesCB")
								.setFallbackUri("forward:/online-learning-platform-courses-fallback")))
						.uri("lb://ONLINELEARNINGPLATFORMCOURSES"))
				.build();
	}
}
