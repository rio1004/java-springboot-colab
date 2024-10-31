package com.example.demo.service.impl;

import com.example.demo.dto.UserRequestDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.vo.userVO.UserResponseFindVO;
import com.example.demo.vo.userVO.UserResponseListVO;
import com.example.demo.vo.userVO.UserResponseVO;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public UserResponseListVO getUsers(UserRequestDto userRequestDto) {
        int page = userRequestDto.getPage();
        int pageSize = userRequestDto.getPageSize();

        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("id").descending());
        Page<User> list;
        if (StringUtils.hasText(userRequestDto.getUsername()) || StringUtils.hasText(userRequestDto.getFirstname()) || StringUtils.hasText(userRequestDto.getLastname())) {
            list = userRepository.findAllByUsernameOrFirstnameOrLastnameOrAddress(userRequestDto.getUsername(), userRequestDto.getFirstname(), userRequestDto.getLastname(), userRequestDto.getAddress(), pageable);
        } else {
            list = userRepository.findAll(pageable);
        }
        System.out.println("LIST TO: " + list.getContent());
        return new UserResponseListVO(HttpStatus.OK.value(), "success", list.getContent(), list.getNumberOfElements());
    }

    
    @Override
    public UserResponseVO postUser(User user) {
        System.out.println("USER TO: " + user);
        userRepository.save(user);
        return new UserResponseVO(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase());
    }

    @Override
    public UserResponseFindVO findUser(Integer id) {
        Optional<User> existingUser = userRepository.findById(id);
        return existingUser.map(user -> new UserResponseFindVO(200, "User found", existingUser))
                .orElseGet(() -> new UserResponseFindVO(404, "User not found", null));
    }

    @Override
    public UserResponseVO updateUser(User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());
        return existingUser.map(item -> {
            item.setUsername(user.getUsername());
            item.setFirstname(user.getFirstname());
            item.setLastname(user.getLastname());
            item.setAddress(user.getAddress());
            userRepository.save(item);
            return new UserResponseVO(HttpStatus.OK.value(), "Updated Successfully");
        }).orElse(new UserResponseVO(HttpStatus.OK.value(), "Update Fail"));
    }

    @Override
    public UserResponseVO deleteUser(Integer id) {
        return userRepository.findById(id).map(item -> {
            userRepository.deleteById(item.getId());
            return new UserResponseVO(HttpStatus.OK.value(), "Deleted Successfully");
        }).orElse(new UserResponseVO(HttpStatus.NOT_FOUND.value(), "User Not Found"));
    }
}
