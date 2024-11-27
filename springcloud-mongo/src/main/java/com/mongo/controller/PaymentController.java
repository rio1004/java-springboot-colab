package com.mongo.controller;

import com.mongo.dto.PaymentCreateRequestDto;
import com.mongo.model.Payment;
import com.mongo.repository.PaymentRepository;
import com.mongo.service.PaymentService;
import com.mongo.vo.ResponseVO;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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
    public ResponseEntity<ResponseVO> getPaymentById(@PathVariable String id) {
        logger.info("Fetching payment with ID: {}", id);

        Optional<Payment> data = paymentRepository.findById(id);

        ResponseVO response = new ResponseVO(200, "Payment created successfully!", data);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/payments")
    public ResponseEntity<ResponseVO> createPayment(@Valid @RequestBody Payment params) {
        logger.info("Creating a new payment: {}", params);

        if (params.getId() != null) {
            paymentRepository.save(params);
            ResponseVO response = new ResponseVO(200, "Payment created successfully!", null);
            logger.info("Payment created successfully with ID: {}", params.getId());
            return ResponseEntity.ok(response);
        } else {
            // If params are null, return a 400 Bad Request with an error message
            logger.error("Payment creation failed: invalid input (null params)");
            ResponseVO errorResponse = new ResponseVO(400, "Invalid payment data", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

}
