package com.example.axieva;

import com.example.axieva.common.enums.UserType;
import com.example.axieva.entity.Users;
import com.example.axieva.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AxievaApplication {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(AxievaApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Users user = new Users("admin@gmail.com", passwordEncoder.encode("admin123"), UserType.ADMIN);
            userRepository.save(user);
        };
    }

}
