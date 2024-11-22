package com.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the MongoDB-backed Spring Boot application.
 * This application manages payments and other services.
 */
@SpringBootApplication
public class MongoApplication {

    /**
     * The main method to start the Spring Boot application.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(MongoApplication.class, args);
    }
}
