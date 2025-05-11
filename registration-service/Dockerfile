# Use Java 17 base image
FROM openjdk:17-jdk-slim

# Copy the JAR file (بعد ما تعمله build)
COPY target/registration-service-0.0.1-SNAPSHOT.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
