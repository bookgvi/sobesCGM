package com.example.sobes.service.securityService;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.SessionScope;

import java.util.UUID;

@Service
@SessionScope
public class UserManagerService {
    private String userName;
    private String password;
    private String token;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public String generateToken() {
        if ((StringUtils.hasLength(userName) && StringUtils.hasLength(password))) {
            this.token = UUID.randomUUID().toString();
        }
        return this.token;
    }

    public void resetAuth() {
        this.token = null;
        this.userName = null;
        this.password = null;
    }
}
