package com.example.roleservice.web;

import com.example.roleservice.model.dto.RoleDto;
import com.example.roleservice.model.entity.Role;
import com.example.roleservice.service.RoleService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
//@Data
@RefreshScope

public class RoleController {
    private final RoleService roleService;
    @Value("${p1}")
    private int p1;

    @GetMapping("refresh")
    public String refresh() {
        System.out.println(p1);
        return p1 + "";
    }

    @GetMapping("/roles")
    public List<RoleDto> getUserRole() {
        return roleService.getAllRoles();
    }

    @GetMapping("/getRolesById/{id}")
    private Optional<RoleDto> getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @GetMapping("/getRolesByUserId/{userId}")
    public List<RoleDto> getRolesByUserId(@PathVariable Long userId) {
        return roleService.getRolesByUserId(userId);
    }

    @PostMapping("/createRole")
    public RoleDto createRole(@RequestBody RoleDto roleDto) {
        return roleService.createRole(roleDto);
    }
}
