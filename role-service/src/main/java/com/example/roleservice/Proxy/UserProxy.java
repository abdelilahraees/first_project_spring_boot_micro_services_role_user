package com.example.roleservice.Proxy;

import com.example.roleservice.model.dto.UserDto;
import org.apache.catalina.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "user-service")
public interface UserProxy {

    @GetMapping("/users/{id}")
    public Optional<UserDto> getUserById(@PathVariable Long id, @RequestParam String source);


    @GetMapping("/usersByRoleId/{roleId}")
    public List<UserDto> getUsersByRoleId(@PathVariable Long roleId);

}
