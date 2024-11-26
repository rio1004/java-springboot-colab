package com.mongo.repository;

import com.mongo.model.Payment;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {

    List<Payment> findByName(String name);
    // List<Payment> findByName(String name);
}
