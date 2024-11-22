package com.example.demo.controller;


import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;
import com.example.demo.vo.RoleVo.RoleResponseFindVO;
import com.example.demo.vo.RoleVo.RoleResponseListVO;
import com.example.demo.vo.RoleVo.RoleResponseVO;

import jakarta.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    public RoleResponseVO addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }

    @GetMapping("/{id}")
    public RoleResponseFindVO findRole(@PathVariable("id") Integer id){
        return roleService.findRole(id); 
    }

    @PutMapping
    public RoleResponseVO updateRole(@RequestBody Role role){
        return roleService.updateRole(role); 
    }

    @DeleteMapping("/{id}")
    public RoleResponseVO removeRole(@PathVariable("id") Integer id){
        return roleService.removeRole(id); 
    }
}
