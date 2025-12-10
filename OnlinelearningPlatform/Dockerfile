#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

# Add curl so healthcheck works
RUN apt-get update && apt-get install -y curl

LABEL "org.opencontainers.image.authors"="OnlineLearningPlatform.com"

COPY target/OnlineLearningPlatform-0.0.1-SNAPSHOT.jar OnlineLearningPlatform-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "OnlineLearningPlatform-0.0.1-SNAPSHOT.jar"]