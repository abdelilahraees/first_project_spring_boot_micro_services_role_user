package com.example.roleservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder

public class RoleDto {
    private long id;
    private String role;
    private String description;
    private Set<Long> userIds;
    private List<UserDto> users; // <UserDto>

    public RoleDto(String role, String description) {
        this.role = role;
        this.description = description;
    }

    public RoleDto(String role, String description, Set<Long> userIds, List<UserDto> users) {
        this.role = role;
        this.description = description;
        this.userIds = userIds;
        this.users = users;
    }
}
