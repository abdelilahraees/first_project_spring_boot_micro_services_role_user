package com.example.roleservice.dao;

import com.example.roleservice.model.entity.User_Role;
import com.example.roleservice.repository.RoleUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserRoleDao {

    private final RoleUserRepo roleUserRepo;


    public User_Role save(User_Role userRole) {
        return roleUserRepo.save(userRole);
    }

    public Set<Long> getRoleIdsByUserId(Long userId) {
        return roleUserRepo.getRoleIdsByUserId(userId);
    }


    public Set<Long> getUserIdsByRoleId(Long roleId) {
        return roleUserRepo.getUserIdsByRoleId(roleId); //todo>
    }
}