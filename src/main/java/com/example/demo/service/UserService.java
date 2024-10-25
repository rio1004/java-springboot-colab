package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.vo.userVO.UserResponseFindVO;
import com.example.demo.vo.userVO.UserResponseListVO;
import com.example.demo.vo.userVO.UserResponsePostVO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    UserResponseListVO getUsers();

    UserResponsePostVO postUser(User user);

    UserResponseFindVO findUser(Integer id);

    UserResponsePostVO updateUser(User user);

    UserResponsePostVO deleteUser(Integer id);

}
