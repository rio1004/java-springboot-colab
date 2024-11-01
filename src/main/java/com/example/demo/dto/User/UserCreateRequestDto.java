package com.example.demo.dto.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserCreateRequestDto {
    private String username; 
    private String firstname; 
    private String lastname; 
    private String address; 
    private Integer roleId; 
}
