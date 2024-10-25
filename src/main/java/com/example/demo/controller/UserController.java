package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.vo.UserResponseListVO;
import com.example.demo.vo.UserResponsePostVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="/v1/users")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping
    public ResponseEntity<UserResponseListVO> getUsers(){
        return  userService.getUsers();
    }

    @PostMapping
    public ResponseEntity<UserResponsePostVO>postUser(@Valid @RequestBody User user) {
        return  userService.postUser(user);
    }
}
