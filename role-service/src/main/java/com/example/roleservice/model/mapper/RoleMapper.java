package com.example.roleservice.model.mapper;

import com.example.roleservice.model.dto.RoleDto;
import com.example.roleservice.model.entity.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleMapper {
  public   Role toRole(RoleDto roleDto) {
        return Role.builder()
                .id(roleDto.getId())
                .role(roleDto.getRole())
                .description(roleDto.getDescription())
                .build();
    }

  public   RoleDto toRoleDto(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .role(role.getRole())
                .description(role.getDescription())
                .build();
    }

}
