# Stage 1: Build the application
FROM maven:3.9.0 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and the source code
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Create the final image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar ./demo-0.0.1-SNAPSHOT.jar

# Expose the application port
EXPOSE 1023

# Run the application
ENTRYPOINT ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]
