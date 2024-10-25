package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.vo.userVO.UserResponseFindVO;
import com.example.demo.vo.userVO.UserResponseListVO;
import com.example.demo.vo.userVO.UserResponseVO;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/v1/users")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping
    public UserResponseListVO getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public UserResponseVO postUser(@Valid @RequestBody User user) {
        return userService.postUser(user);
    }
    //
    @GetMapping("/{id}")
    public UserResponseFindVO findUser(@PathVariable("id") Integer id) {
        return userService.findUser(id);
    }

    @PutMapping
    public UserResponseVO updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public UserResponseVO deleteUser(@PathVariable("id") Integer id){
        return  userService.deleteUser(id);
    }
}
