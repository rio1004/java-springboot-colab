package com.mongo.repository;

import com.mongo.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {
    // No need to override findById unless custom logic is required
}
