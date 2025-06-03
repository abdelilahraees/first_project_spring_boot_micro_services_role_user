package com.example.roleservice.repository;

import com.example.roleservice.model.entity.User_Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleUserRepo extends JpaRepository<User_Role, Long> {

    @Query("SELECT ur.roleId FROM User_Role ur WHERE ur.userId = :userId")
    Set<Long> getRoleIdsByUserId(Long userId);


    @Query("SELECT ur.userId FROM User_Role ur WHERE ur.roleId = :roleId")
    Set<Long> getUserIdsByRoleId(Long roleId);
}
