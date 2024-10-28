package com.example.demo.dto;

import lombok.Data;

@Data
public class UserRequestDto extends PaginationRequestDto {
    private String username;
    private String firstname;
    private String lastname;
    private String address;
}
