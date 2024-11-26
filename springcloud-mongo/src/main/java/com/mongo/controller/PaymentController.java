package com.mongo.controller;

import com.mongo.model.Payment;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mongo.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    // @GetMapping("/payments/by-id/{id}")
    // public ResponseEntity<Payment> getPaymentById(@PathVariable String id) {
    // logger.info("Fetching payment with ID: {}", id);
    // return paymentService.getProductByName(id); // Adjust logic as necessary
    // }

    // @GetMapping("/payments/by-name/{name}")
    // public ResponseEntity<Payment> getPaymentByName(@PathVariable String name) {
    // logger.info("Fetching payment with name: {}", name);
    // return paymentService.getProductByName(name);
    // }

    @PostMapping("/payments/test")
    public ResponseEntity<List<Payment>> getTest(@RequestBody Map<String, Object> paramsBody) {
        String paymentName = (String) paramsBody.get("name");

        logger.info("Fetching payment with ID: {}", paymentName);
 
        List<Payment> payments = (paymentName != null)
                ? paymentService.getPaymentsByName(paymentName)
                : paymentService.getAllPayments();

        return ResponseEntity.ok(payments);
    }

}
