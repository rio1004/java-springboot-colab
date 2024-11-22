package com.example.demo.service.impl;

import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import com.example.demo.vo.RoleVo.RoleResponseFindVO;
import com.example.demo.vo.RoleVo.RoleResponseListVO;
import jakarta.annotation.Resource;
import jakarta.websocket.server.PathParam;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.vo.RoleVo.RoleResponseVO;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleRepository roleRepository;

    @Override
    public RoleResponseListVO getRoles(){
        List<Role> list = roleRepository.findAll();
        return new RoleResponseListVO(HttpStatus.OK.value(), "success", list, list.size());
    }

    @Override
    public RoleResponseVO addRole(Role role){
        roleRepository.save(role);
        return new RoleResponseVO(HttpStatus.OK.value(), "success");
    }

    @Override
    public RoleResponseFindVO findRole(@PathParam("id") Integer id){
        Optional<Role> existingRole = roleRepository.findById(id);
        return existingRole.map(role -> new RoleResponseFindVO(HttpStatus.OK.value(), "success", role)).orElse(new RoleResponseFindVO(HttpStatus.NOT_FOUND.value(), "role not found", null));
    }

    @Override
    public RoleResponseVO updateRole(Role role){
        Optional<Role> existingRole = roleRepository.findById(role.getId());
        return existingRole.map(item->{
            item.setName(role.getName());
            item.setDescription(role.getDescription());
            item.setType(role.getType());
            roleRepository.save(item);

            return new RoleResponseVO(HttpStatus.OK.value(), "Updated Successfully"); 
        }).orElse( new RoleResponseVO(HttpStatus.NOT_FOUND.value(), null));
    }

       @Override
    public RoleResponseVO removeRole(@PathParam("id") Integer id) {
        Optional<Role> existingRole = roleRepository.findById(id);
        return existingRole.map(item -> {
            try {
                if (hasActiveUsers(item)) {
                    return new RoleResponseVO(HttpStatus.CONFLICT.value(), 
                        "Cannot delete role: Still has active users");
                }
                roleRepository.delete(item);
                return new RoleResponseVO(HttpStatus.OK.value(), "Deleted Successfully");
            } catch (DataIntegrityViolationException e) {
                return new RoleResponseVO(HttpStatus.CONFLICT.value(), 
                    "Cannot delete role: It is referenced by other entities");
            } catch (Exception e) {
                return new RoleResponseVO(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
                    "Error deleting role: " + e.getMessage());
            }
        }).orElse(new RoleResponseVO(HttpStatus.NOT_FOUND.value(), "Role not found"));
    }

    // Optional: Helper method to check for dependencies
    private boolean hasActiveUsers(Role role) {
        // Implement your logic to check if role has active users
        // Example: return userRepository.countByRoleId(role.getId()) > 0;
        return false;
    }
}
