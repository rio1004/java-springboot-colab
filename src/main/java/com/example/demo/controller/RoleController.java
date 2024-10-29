package com.example.demo.controller;


import com.example.demo.service.RoleService;
import com.example.demo.vo.RoleVo.RoleResponseListVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/roles")
public class RoleController {
    @Resource
    private RoleService roleService;


    @GetMapping
    public RoleResponseListVO getRoles(){
        return  roleService.getRoles();
    }

}
