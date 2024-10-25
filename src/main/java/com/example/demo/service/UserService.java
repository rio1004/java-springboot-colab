package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.vo.UserResponseListVO;
import com.example.demo.vo.UserResponsePostVO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<UserResponseListVO> getUsers();

    ResponseEntity<UserResponsePostVO> postUser(User user);
}
