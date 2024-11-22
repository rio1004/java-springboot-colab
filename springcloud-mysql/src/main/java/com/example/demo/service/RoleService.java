package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.vo.RoleVo.RoleResponseFindVO;
import com.example.demo.vo.RoleVo.RoleResponseListVO;
import com.example.demo.vo.RoleVo.RoleResponseVO;

public interface RoleService {
    RoleResponseListVO getRoles();
    RoleResponseVO addRole(Role role);
    RoleResponseFindVO findRole(Integer id);
    RoleResponseVO updateRole(Role role); 
    RoleResponseVO removeRole(Integer id);
}
