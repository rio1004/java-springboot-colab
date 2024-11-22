package com.example.demo.vo.userVO;

import com.example.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserResponseListVO {

    private int code;

    private String message;

    private List<User> data;

    private int total;

}
