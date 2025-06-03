package com.example.roleservice.service;

import com.example.roleservice.dao.UserRoleDao;
import com.example.roleservice.model.entity.User_Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleDao userRoleDao;

    @Override
    public Set<Long> getRoleIdsByUserId(Long userId) {
        return userRoleDao.getRoleIdsByUserId(userId);
    }

    @Override
    public Set<Long> getUserIdsByRoleId(Long roleId) {
        return userRoleDao.getUserIdsByRoleId(roleId);
    }


    @Override
    public User_Role addRoleToUser(Long userId,Long roleId) {
        return userRoleDao.save(new User_Role(userId,roleId));

    }
}
