package com.example.axieva.controller;

import com.example.axieva.interfaces.UserInterface;
import com.example.axieva.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    @Autowired private UserInterface userInterface;

    @PostMapping("/createUser")
    public ResponseEntity createUser(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody Map<String, Object> userObject){

        try {
            userInterface.createUser(userObject, userPrincipal);
            return new ResponseEntity<>("User created successfully", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error while creating the user");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/login")
    public ResponseEntity login(@RequestBody Map<String, String> login){

        try{
            String bearerToken = userInterface.loginUser(login);
            System.out.println(bearerToken);
            return new ResponseEntity<>(bearerToken, HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Failed to login", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
