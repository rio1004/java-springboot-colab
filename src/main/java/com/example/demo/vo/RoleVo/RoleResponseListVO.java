package com.example.demo.vo.RoleVo;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RoleResponseListVO {
    private int code;
    private String message;
    private List<Role> data;
    private int total;
}
