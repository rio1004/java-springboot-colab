package com.mongo.service;

import com.mongo.model.Payment;
import com.mongo.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll(); // Fetch all payments
    }

    public Optional<Payment> getPaymentsByName(String id) {
        return paymentRepository.findById(id); // Fetch payments by name
    }

    public Optional<Payment> getPaymentsById(String name) {
        return paymentRepository.findById(name); // Fetch payments by name
    }
}
