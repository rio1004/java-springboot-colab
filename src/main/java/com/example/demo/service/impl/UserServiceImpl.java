package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.vo.userVO.UserResponseFindVO;
import com.example.demo.vo.userVO.UserResponseListVO;
import com.example.demo.vo.userVO.UserResponsePostVO;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public UserResponseListVO getUsers() {
        List<User> list = userRepository.findAll();
        return new UserResponseListVO(HttpStatus.OK.value(), "success", list, list.size());
    }

    @Override
    public UserResponsePostVO postUser(User user) {
        userRepository.save(user);
        return new UserResponsePostVO(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase());
    }

    @Override
    public UserResponseFindVO findUser(Integer id) {
        Optional<User> existingUser = userRepository.findById(id);
        return existingUser.map(user -> new UserResponseFindVO(200, "User found", existingUser))
                .orElseGet(() -> new UserResponseFindVO(404, "User not found", null));
    }

    @Override
    public UserResponsePostVO updateUser(User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());
        return existingUser.map(item -> {
            item.setUsername(user.getUsername());
            item.setFirstname(user.getFirstname());
            item.setLastname(user.getLastname());
            item.setAddress(user.getAddress());
            userRepository.save(item);
            return new UserResponsePostVO(HttpStatus.OK.value(), "Updated Successfully");
        }).orElse(new UserResponsePostVO(HttpStatus.OK.value(), "Update Fail"));
    }

    @Override
    public UserResponsePostVO deleteUser(Integer id) {
        return userRepository.findById(id).map(item -> {
            userRepository.deleteById(item.getId());
            return new UserResponsePostVO(HttpStatus.OK.value(), "Deleted Successfully");
        }).orElse(new UserResponsePostVO(HttpStatus.NOT_FOUND.value(), "User Not Found"));
    }
}
