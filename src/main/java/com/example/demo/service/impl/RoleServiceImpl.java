package com.example.demo.service.impl;

import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import com.example.demo.vo.RoleVo.RoleResponseListVO;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleRepository roleRepository;

    public RoleResponseListVO getRoles(){
        List<Role> list = roleRepository.findAll();
        return new RoleResponseListVO(HttpStatus.OK.value(), "success", list, list.size());
    }

}
