package com.example.roleservice.service;

import com.example.roleservice.model.dto.RoleDto;
import com.example.roleservice.model.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RoleService {
    Long getRoleCount();
    List<RoleDto> getAllRoles();

    Optional<RoleDto> getRoleById(Long id);

    RoleDto createRole(RoleDto roleDto);

    boolean deleteRole(Long id);
    List<RoleDto> getRolesByUserId(Long id);


}
