package com.example.axieva.security;

import com.example.axieva.entity.Users;
import com.example.axieva.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        Optional<Users> existingRecord = userRepository.findByEmailId(username);
        if(existingRecord.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return UserPrincipal.create(existingRecord.get());
    }
}
