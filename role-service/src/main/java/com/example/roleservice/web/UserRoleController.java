package com.example.roleservice.web;

import com.example.roleservice.model.entity.User_Role;
import com.example.roleservice.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class UserRoleController {
    private final UserRoleService userRoleService;


    @GetMapping("/rolesIds/{userId}")
    public Set<Long> getRoleIdsByUserId(@PathVariable Long userId) {
        return userRoleService.getRoleIdsByUserId(userId);
    }

    @GetMapping("userIds/{roleId}")
    public Set<Long> getUserIdsByRoleId(@PathVariable Long roleId) {
        return userRoleService.getUserIdsByRoleId(roleId);
    }

    @PostMapping("roleToUser")
    public User_Role addRoleToUser(@RequestParam long userId, @RequestParam long roleId) {
        return userRoleService.addRoleToUser(userId, roleId);
    }

}
