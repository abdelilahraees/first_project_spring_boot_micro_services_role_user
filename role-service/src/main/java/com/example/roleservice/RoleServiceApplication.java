package com.example.roleservice;

import com.example.roleservice.dao.RoleDao;
import com.example.roleservice.model.dto.RoleDto;
import com.example.roleservice.model.entity.Role;
import com.example.roleservice.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
@RefreshScope
public class RoleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoleServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(RoleDao roleDao) {
        return args -> {
            if (roleDao.getRolesCount() == 0) {
                for (int i = 0; i < 20; i++) {

                    roleDao.createRole(new Role("Role " + i, "Description " + i));
                }

            }


        };
    }

}
