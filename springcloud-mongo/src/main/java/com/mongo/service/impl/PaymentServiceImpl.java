package com.mongo.service.impl;

import com.mongo.model.Payment;
import com.mongo.repository.PaymentRepository;
import com.mongo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public ResponseEntity<Payment> getProductById(Integer id) {
        Optional<Payment> payment = paymentRepository.HanapinBaseSaPangalan(id);
        return payment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
