#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

# Add curl so healthcheck works
RUN apt-get update && apt-get install -y curl

LABEL "org.opencontainers.image.authors"="OnlineLearningPlatformGateway.com"

COPY target/OnlineLearningPlatformApiGatewayHystrix-0.0.1-SNAPSHOT.jar OnlineLearningPlatformApiGatewayHystrix-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "OnlineLearningPlatformApiGatewayHystrix-0.0.1-SNAPSHOT.jar"]