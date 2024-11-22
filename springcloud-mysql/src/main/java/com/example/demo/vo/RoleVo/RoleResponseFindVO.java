package com.example.demo.vo.RoleVo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleResponseFindVO {
    private Integer code;
    private String message;
    private Object role;
}
