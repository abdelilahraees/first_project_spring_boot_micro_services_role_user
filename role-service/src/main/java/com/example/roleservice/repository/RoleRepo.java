package com.example.roleservice.repository;

import com.example.roleservice.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RoleRepo extends JpaRepository<Role,Long> {
}
