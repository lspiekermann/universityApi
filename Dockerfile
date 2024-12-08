FROM eclipse-temurin:21-jdk-alpine

# Copy the jar file to the container
COPY build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app.jar"]
