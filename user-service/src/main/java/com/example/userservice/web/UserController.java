package com.example.userservice.web;

import com.example.userservice.model.dto.UserDto;
import com.example.userservice.service.UserService;
import jakarta.validation.constraints.Max;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public List<UserDto> getUser() {

        return userService.getAllUsers();
    }

    @GetMapping("/usersByRoleId/{roleId}")
    public List<UserDto> getUsersByRoleId(@PathVariable Long roleId) {
        return userService.getUsersByRoleId(roleId);
    }


    @GetMapping("/users/{id}")
    public Optional<UserDto> getUserById(@PathVariable Long id, @RequestParam String source) {
        return userService.getUserById(id, source);
    }

    @PostMapping("/users")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }
}
