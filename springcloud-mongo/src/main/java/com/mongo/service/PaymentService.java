package com.mongo.service;

import com.mongo.model.Payment;
import org.springframework.http.ResponseEntity;

public interface PaymentService {
    ResponseEntity<Payment> getProductById(Integer id);
}