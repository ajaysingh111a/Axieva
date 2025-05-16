package com.example.axieva.interfaces;

import com.example.axieva.security.UserPrincipal;

import java.util.Map;

public interface UserInterface {

    void createUser(Map<String, Object> map, UserPrincipal userPrincipal) throws Exception;

    String loginUser(Map<String, String> login) throws Exception;
}
