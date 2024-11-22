package com.mongo.repository;

import com.mongo.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {
    Optional<Payment> HanapinBaseSaPangalan(String name);
//    Page<Payment> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
