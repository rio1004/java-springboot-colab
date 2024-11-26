package com.mongo.dto;

import lombok.Data;

@Data
public class PaymentCreateRequestDto {
    private String name;
    private String date;
    private Double amount; // Assuming you have an amount field

    // Getters and setters
}
