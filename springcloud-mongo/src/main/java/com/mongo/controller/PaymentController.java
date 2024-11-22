package com.mongo.controller;

import com.mongo.model.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mongo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Integer id) {
        // Log the ID
        logger.info("Fetching payment with ID: {}", id);
        // Delegate to the service
        return paymentService.getProductById(id);
    }
}

