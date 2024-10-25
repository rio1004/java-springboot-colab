package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.vo.userVO.UserResponseFindVO;
import com.example.demo.vo.userVO.UserResponseListVO;
import com.example.demo.vo.userVO.UserResponseVO;

public interface UserService {
    UserResponseListVO getUsers();

    UserResponseVO postUser(User user);

    UserResponseFindVO findUser(Integer id);

    UserResponseVO updateUser(User user);

    UserResponseVO deleteUser(Integer id);

}
