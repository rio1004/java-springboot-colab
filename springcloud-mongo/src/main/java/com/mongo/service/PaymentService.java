package com.mongo.service;

import com.mongo.model.Payment;
import com.mongo.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll(); // Fetch all payments
    }

    public List<Payment> getPaymentsByName(String name) {
        return paymentRepository.findByName(name); // Fetch payments by name
    }
}
