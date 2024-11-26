package com.mongo.controller;

import com.mongo.dto.PaymentCreateRequestDto;
import com.mongo.model.Payment;
import com.mongo.repository.PaymentRepository;
import com.mongo.service.PaymentService;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/payments/list")
    public ResponseEntity<List<Payment>> getList() {
        logger.info("Fetching payment method list");
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/payments/{id}")
    public Optional<Payment> getPaymentById(@PathVariable String id) {
        logger.info("Fetching payment with ID: {}", id);
        return paymentRepository.findById(id);
    }

    @PostMapping("/payments")
    public ResponseEntity<Payment> createPayment(@Valid @RequestBody PaymentCreateRequestDto params) {
        logger.info("Creating a new payment: {}", params);
    
        // Map the DTO to the Payment entity
        Payment payment = new Payment();
        payment.setName(params.getName());
        payment.setDate(params.getDate());
        payment.setAmount(params.getAmount()); // Assuming the DTO has an amount field
    
        // Save the payment to the repository
        Payment savedPayment = paymentRepository.save(payment);
    
        logger.info("Payment created successfully with ID: {}", savedPayment.getId());
        return ResponseEntity.ok(savedPayment);
    }

}
