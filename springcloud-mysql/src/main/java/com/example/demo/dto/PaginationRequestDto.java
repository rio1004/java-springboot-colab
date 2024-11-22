package com.example.demo.dto;

import lombok.Data;

@Data
public class PaginationRequestDto {
    private Integer page = 1;
    private Integer pageSize = 10;
}
