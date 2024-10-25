package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.vo.UserResponseListVO;
import com.example.demo.vo.UserResponsePostVO;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public ResponseEntity<UserResponseListVO> getUsers() {
        List<User> list = userRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK.value()).body(new UserResponseListVO(HttpStatus.OK.value(), "success", list, list.size()));
    }

    @Override
    public ResponseEntity<UserResponsePostVO> postUser(User user) {
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(new UserResponsePostVO(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase()));
    }
}
