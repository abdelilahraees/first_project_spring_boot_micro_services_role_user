package com.example.userservice.service;

import com.example.userservice.dao.UserDao;
import com.example.userservice.model.dto.Role;
import com.example.userservice.model.dto.UserDto;
import com.example.userservice.model.dto.User_Role;
import com.example.userservice.model.entity.User;
import com.example.userservice.model.mapper.UserMapper;
import com.example.userservice.proxy.RoleProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final UserMapper userMapper;
    private final RoleProxy roleProxy;

    @Override
    public List<UserDto> getAllUsers() {

        return userDao.getAllUsers().stream().map(userMapper::toUserDto).peek(userDto -> {

            if (roleProxy.getRolesByUserId(userDto.getId()) != null) {
                userDto.setRoles(roleProxy.getRolesByUserId(userDto.getId()));
            }
            userDto.setRoles(roleProxy.getRolesByUserId(userDto.getId()));
        }).toList();
    }

    @Override
    public long getUsersCount() {
        return userDao.getUserCount();
    }


    public List<Role> getRolesByUserId(Long userId) {
        return roleProxy.getRolesByUserId(userId);
    }

    @Override
    public Optional<UserDto> getUserById(Long id, String source) {

        return userDao.getUserById(id).map(userMapper::toUserDto).map(userDto -> {
            if (!source.equals("role-service")) {
                userDto.setRoles(this.getRolesByUserId(id));

            }

            return userDto;
        });
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userDto != null) {
            User user = userMapper.toUser(userDto);
            user = userDao.createUser(user);
            userDto.setId(user.getId());
            List<Long> roleIds = RolesToUser(userDto);
            userDto.setRoleIds(roleIds);
            userDto.setRoles(this.getRolesByUserId(user.getId()));
        }

        return userDto;

    }

    @Override
    public List<UserDto> getUsersByRoleId(Long roleId) {
        Set<Long> userIds = roleProxy.getUserIdsByRoleId(roleId);
        return userIds.stream()
                .map(userId -> this.getUserById(userId, "role-service"))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }


    public List<Long> RolesToUser(UserDto userDto) {
        List<Long> roleIds = new ArrayList<>();
        if (userDto.getRoleIds() != null) {
            for (Long role_id : userDto.getRoleIds()) {
                User_Role userRole = roleProxy.addRoleToUser(userDto.getId(), role_id);
                System.out.println(userDto.getId());
                roleIds.add(userRole.getRoleId());
            }
        }

        return roleIds;
    }
}
