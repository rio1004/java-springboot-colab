package com.mongo.service;

import com.example.model.Payment;
import com.example.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(String id) {
        return paymentRepository.findById(id);
    }

    public Payment createPayment(String userId, Double amount, String status) {
        Payment payment = new Payment(userId, amount, status, LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    public void deletePayment(String id) {
        paymentRepository.deleteById(id);
    }
}
