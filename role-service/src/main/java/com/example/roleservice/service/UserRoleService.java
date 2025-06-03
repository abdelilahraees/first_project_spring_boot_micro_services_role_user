package com.example.roleservice.service;

import com.example.roleservice.model.entity.User_Role;

import java.util.List;
import java.util.Set;

public interface UserRoleService {
    Set<Long> getRoleIdsByUserId(Long userId);

    Set<Long> getUserIdsByRoleId(Long roleId);

    User_Role addRoleToUser(Long userId ,Long roleId);


}
