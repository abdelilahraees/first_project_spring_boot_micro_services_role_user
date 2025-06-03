package com.example.userservice.proxy;

import com.example.userservice.model.dto.Role;
import com.example.userservice.model.dto.User_Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@FeignClient(name = "role-service")
@Service
public interface RoleProxy {
    @GetMapping("/roles")
    public List<Role> getUserRole();

    @GetMapping("/roles/{id}")
    public Optional<Role> getRoleById(@PathVariable Long id);

    @GetMapping("/getRolesByUserId/{userId}")
    public List<Role> getRolesByUserId(@PathVariable Long userId);



    @PostMapping("roleToUser")
    public User_Role addRoleToUser(@RequestParam long userId, @RequestParam long roleId);


    @GetMapping("userIds/{roleId}")
    public Set<Long> getUserIdsByRoleId(@PathVariable Long roleId);

}
