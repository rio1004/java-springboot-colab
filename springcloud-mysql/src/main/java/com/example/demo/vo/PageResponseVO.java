package com.example.demo.vo;

import com.example.demo.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageResponseVO {

    private int code;

    private String message;

    private List<Product> data;

    private int total;
}
