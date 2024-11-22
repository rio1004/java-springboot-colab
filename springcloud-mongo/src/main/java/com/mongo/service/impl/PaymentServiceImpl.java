package com.mongo.service.impl;

import com.mongo.repository.PaymentRepository;
import com.mongo.vo.ResponseVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentServiceImpl {
    @Resource
    private PaymentRepository paymentRepository;

    public ResponseEntity<ResponseVO> testMuna(){
        ResponseVO response = new ResponseVO( 666   , "sana gumana");
        return ResponseEntity.ok(response);
    }
}
