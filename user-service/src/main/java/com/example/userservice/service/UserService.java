package com.example.userservice.service;

import com.example.userservice.model.dto.UserDto;
import com.example.userservice.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getAllUsers();

    long getUsersCount();

    Optional<UserDto> getUserById(Long id, String source);


    UserDto createUser(UserDto userDto);

    List<UserDto> getUsersByRoleId(Long roleId);


}
