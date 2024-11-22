package com.example.demo.dto.User;

import com.example.demo.dto.PaginationRequestDto;

import lombok.Data;

@Data
public class UserRequestDto extends PaginationRequestDto {
    private String username;
    private String firstname;
    private String lastname;
    private String address;
}
