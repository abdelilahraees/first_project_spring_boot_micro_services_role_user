package com.example.userservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class UserDto {
    private long id;
    private String userName;
    private String email;
    private List<Long> roleIds; // <==>
    private List<Role> roles;

    public UserDto(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }
}
