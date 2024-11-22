package com.example.demo.service;

import com.example.demo.dto.User.UserCreateRequestDto;
import com.example.demo.dto.User.UserRequestDto;
import com.example.demo.entity.User;
import com.example.demo.vo.userVO.UserResponseFindVO;
import com.example.demo.vo.userVO.UserResponseListVO;
import com.example.demo.vo.userVO.UserResponseVO;

public interface UserService {
    UserResponseListVO getUsers(UserRequestDto userRequestDto);

    UserResponseVO postUser(UserCreateRequestDto userDto);

    UserResponseFindVO findUser(Integer id);

    UserResponseVO updateUser(User user);

    UserResponseVO deleteUser(Integer id);

}
