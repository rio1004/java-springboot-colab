package com.example.demo.dto;

import com.example.demo.entity.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class ProductDto extends PaginationRequestDto{
    private String name;
}
