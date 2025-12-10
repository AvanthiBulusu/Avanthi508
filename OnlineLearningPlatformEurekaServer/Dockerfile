FROM openjdk:17-jdk-slim

# Add curl so healthcheck works
RUN apt-get update && apt-get install -y curl

LABEL "org.opencontainers.image.authors" = "OLPEurekaServer.com"

COPY target/OnlineLearningPlatformEurekaServer-0.0.1-SNAPSHOT.jar OnlineLearningPlatformEurekaServer-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "OnlineLearningPlatformEurekaServer-0.0.1-SNAPSHOT.jar"]