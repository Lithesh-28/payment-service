# Step 1: Use a lightweight OpenJDK image
FROM openjdk:21-jdk-slim

# Step 2: Set working directory inside container
WORKDIR /app

# Step 3: Copy the JAR file from target folder
COPY target/payment-service-0.0.1-SNAPSHOT.jar payment-service.jar

# Step 4: Expose the application port
EXPOSE 8083

# Step 5: Run the JAR file
ENTRYPOINT ["java", "-jar", "payment-service.jar"]
