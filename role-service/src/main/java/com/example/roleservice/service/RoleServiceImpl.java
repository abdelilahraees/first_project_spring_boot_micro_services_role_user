package com.example.roleservice.service;

import com.example.roleservice.Proxy.UserProxy;
import com.example.roleservice.dao.RoleDao;
import com.example.roleservice.model.dto.RoleDto;
import com.example.roleservice.model.dto.UserDto;
import com.example.roleservice.model.entity.Role;
import com.example.roleservice.model.entity.User_Role;
import com.example.roleservice.model.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.aspectj.weaver.loadtime.definition.LightXMLParser;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.toList;


@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;
    private final RoleMapper roleMapper;
    private final UserRoleService userRoleService;
    private final UserProxy userProxy;


    public Long getRoleCount() {
        return roleDao.getRolesCount();
    }


    @Override
    public List<RoleDto> getAllRoles() {
        return roleDao.getRoles().stream().map(roleMapper::toRoleDto).peek(
                roleDto -> {
                    Set<Long> userIds = userRoleService.getUserIdsByRoleId(roleDto.getId());
                    roleDto.setUserIds(userIds);
                    System.out.println(userIds);
                    List<UserDto> users = userIds.stream().map(userId -> userProxy.getUserById(userId, "role-service").orElse(null)).toList();
                    roleDto.setUsers(users);
                }
        ).toList();


    }

    @Override
    public Optional<RoleDto> getRoleById(Long id) {
        return roleDao.getRoleById(id).map(roleMapper::toRoleDto).map(roleDto -> {
            Set<Long> userIds = userRoleService.getUserIdsByRoleId(roleDto.getId());
            roleDto.setUserIds(userIds);
            List<UserDto> users = userIds.stream().map(userId -> userProxy.getUserById(userId, "role-service").orElse(null)).toList();
            roleDto.setUsers(users);
            return roleDto;

        });
    }

    @Override
    public RoleDto createRole(RoleDto roleDto) {
        Role role = roleMapper.toRole(roleDto);
        role = roleDao.createRole(role);
        roleDto.setId(role.getId());
        Set<Long> userIds = usersToRole(roleDto);
        roleDto.setUserIds(userIds);
        System.out.println("----->" + role.getId());
        roleDto.setUsers(userProxy.getUsersByRoleId(role.getId()));
        return roleDto;
    }


    public Set<Long> usersToRole(RoleDto roleDto) {
        Set<Long> userIds = new HashSet<>();
        if (roleDto.getUserIds() != null) {
            for (Long user_id : roleDto.getUserIds()) {
                User_Role userRole = userRoleService.addRoleToUser(user_id, roleDto.getId());
                userIds.add(userRole.getUserId());
            }
        }

        return userIds;
    }


    @Override
    public boolean deleteRole(Long id) {
        return false;
    }

    @Override
    public List<RoleDto> getRolesByUserId(Long id) {
        Set<Long> roleIds = userRoleService.getRoleIdsByUserId(id); //todo>
        if (!roleIds.isEmpty()) {
            return roleIds.stream().map(this::getRoleById).map(roleDto -> {
                return roleDto.orElse(null);
            }).toList();
        }
        return Collections.emptyList();
    }
}
