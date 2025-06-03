package com.example.roleservice.dao;

import com.example.roleservice.model.entity.Role;
import com.example.roleservice.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleDao {
    private final RoleRepo roleRepo;

    public List<Role> getRoles() {
        return roleRepo.findAll();
    }

    public Optional<Role> getRoleById(long id) {
        return roleRepo.findById(id);
    }

    public long getRolesCount() {
        return roleRepo.count();
    }

    public Role createRole(Role role) {
        return roleRepo.save(role);

    }



    public void deleteById(long id) {
        roleRepo.deleteById(id);
    }

    public boolean existsById(long id) {
        return roleRepo.existsById(id);
    }


}
