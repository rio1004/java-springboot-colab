package com.mongo.controller;

import com.mongo.model.Payment;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mongo.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payments/list")
    public ResponseEntity<List<Payment>> getList() {
        logger.info("Fetching payment with name: {}");
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/payments/{id}")
    public Optional<Payment> getPaymentById(@PathVariable String id) {
        logger.info("Fetching payment with ID: {}", id);
        return paymentService.getPaymentsById(id); // Adjust logic as necessary
    }

}
