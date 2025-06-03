package com.example.userservice;

import com.example.userservice.dao.UserDao;
import com.example.userservice.model.dto.UserDto;
import com.example.userservice.model.entity.User;
import com.example.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(UserDao userDao) {
        return args -> {
            if (userDao.getUserCount() == 0) {
                for (int i = 0; i < 20; i++) {
                    userDao.createUser(new User("user" + i, "@gmail.com" + i));
                }
            }
        };
    }


}
