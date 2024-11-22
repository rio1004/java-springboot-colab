package com.mongo.repository;

import com.mongo.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {
    // Custom query with @Query annotation
    @Query("{ 'id': ?0 }")
    Optional<Payment> HanapinBaseSaPangalan(Integer id);

    // Query derivation method
    Page<Payment> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
