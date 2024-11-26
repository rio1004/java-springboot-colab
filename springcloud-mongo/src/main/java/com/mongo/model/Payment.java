package com.mongo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

@Data
@Document(collection = "payments")
public class Payment {
    @Id
    private String id;

    @Indexed  // Creates an index on the 'userId' field
    private String userId;
    private String type;
    private String name;
    private String date;

    private Double amount;
    private String status;
    private LocalDateTime timestamp;

    // Constructors, getters, and setters
    // public Payment() {}

    // public Payment(String userId, Double amount, String status, LocalDateTime timestamp) {
    //     this.userId = userId;
    //     this.amount = amount;
    //     this.status = status;
    //     this.timestamp = timestamp;
    // }

}
