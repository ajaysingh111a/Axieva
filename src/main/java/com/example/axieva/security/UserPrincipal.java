package com.example.axieva.security;

import com.example.axieva.common.enums.UserType;
import com.example.axieva.entity.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;

    @JsonIgnore
    private String password;
    private String username;
    private UserType userType;

    public UserPrincipal(String emailId, UserType userType){
        this.username = emailId;
        this.userType = userType;
    }

    public static UserPrincipal create(Users user) {
        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority(user.getUserType().name())
        );
        UserPrincipal principal = new UserPrincipal(user.getEmailId(), user.getUserType());
        principal.setAuthorities(authorities);
        principal.setPassword(user.getPassword());
        return principal;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
