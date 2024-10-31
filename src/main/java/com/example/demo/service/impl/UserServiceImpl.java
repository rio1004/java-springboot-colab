package com.example.demo.service.impl;

import com.example.demo.dto.User.UserCreateRequestDto;
import com.example.demo.dto.User.UserRequestDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.vo.userVO.UserResponseFindVO;
import com.example.demo.vo.userVO.UserResponseListVO;
import com.example.demo.vo.userVO.UserResponseVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository; 

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
    public UserResponseVO postUser(UserCreateRequestDto userDto) {
        
        log.info("USER TO: {} , {}, {},{},{}",userDto);
        User user = new User(); 
        log.info("USER TO: {} , {}, {},{},{}",user);    
        user.setUsername(userDto.getUsername());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setAddress(userDto.getAddress());

        log.info("ROLE TO: {}",userDto.getRoleId());
        Optional<Role> role = roleRepository.findById(userDto.getRoleId());
        if(role.isPresent()){
            user.setRole(role.get());
            userRepository.save(user);
            return new UserResponseVO(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase());
        }else{
            return new UserResponseVO(HttpStatus.NOT_FOUND.value(), "gagi wala dyan");
        }
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
        log.info("ROLE ID: {}", user);
        return existingUser.map(item -> {
            item.setUsername(user.getUsername());
            item.setFirstname(user.getFirstname());
            item.setLastname(user.getLastname());
            item.setAddress(user.getAddress());

            // Get role ID, either from user input or default
            Integer roleId = (user.getRole() != null && user.getRole().getId() != null) 
                ? user.getRole().getId() 
                : 2; // default role ID

            // Check if role exists in database
            Optional<Role> role = roleRepository.findById(roleId);
            if (!role.isPresent()) {
                return new UserResponseVO(HttpStatus.NOT_FOUND.value(), "Role not found");
            }

            // Set the verified role
            item.setRole(role.get());
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
