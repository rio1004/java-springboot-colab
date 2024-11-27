package com.mongo.vo;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mongo.model.Payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseVO {

    private int code;
    private String message;
    private Optional<Payment> data;

}
