package com.example.demo.vo.userVO;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseFindVO {
    private int code;
    private String message;
    private Object user;
}
