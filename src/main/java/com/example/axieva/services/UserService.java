package com.example.axieva.services;

import com.example.axieva.common.enums.UserType;
import com.example.axieva.entity.Users;
import com.example.axieva.interfaces.UserInterface;
import com.example.axieva.repository.UserRepository;
import com.example.axieva.security.JwtUtil;
import com.example.axieva.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService implements UserInterface {

    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private UserRepository userRepository;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtUtil jwtUtil;

    @Override
    public void createUser(Map<String, Object> map, UserPrincipal userPrincipal) throws Exception {

        UserType currentType = userPrincipal.getUserType();
        UserType requestType = UserType.valueOf(String.valueOf(map.get("userType")));
        System.out.println(currentType);
        if(currentType.equals(UserType.ADMIN)){
            Users user = new Users(String.valueOf(map.get("emailId")),
                    passwordEncoder.encode(String.valueOf(map.get("password"))), UserType.valueOf(String.valueOf(map.get("userType"))));
            userRepository.save(user);
        }else if(currentType.equals(UserType.STUDENT) && requestType.equals(UserType.STUDENT)){
            Users user = new Users(String.valueOf(map.get("emailId")),
                    passwordEncoder.encode(String.valueOf(map.get("password"))), UserType.valueOf(String.valueOf(map.get("userType"))));
            userRepository.save(user);
        }
        else if(currentType.equals(UserType.TEACHER) && requestType.equals(UserType.TEACHER)){
            Users user = new Users(String.valueOf(map.get("emailId")),
                    passwordEncoder.encode(String.valueOf(map.get("password"))), UserType.valueOf(String.valueOf(map.get("userType"))));
            userRepository.save(user);
        }
        else{
            throw new Exception("You are unauthorized to perform this operation");
        }
    }

    @Override
    public String loginUser(Map<String, String> login) throws Exception {

        String emailId = String.valueOf(login.get("emailId"));
        String password = String.valueOf(login.get("password"));
        Optional<Users> users = userRepository.findByEmailId(emailId);
        if(users.isEmpty()){
            throw new Exception("User not found");
        }

        if (!passwordEncoder.matches(password, users.get().getPassword())) {
            throw new Exception("Password mismatched");
        }

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(emailId, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtUtil.generateToken(userDetails);
        return jwt;
    }
}
