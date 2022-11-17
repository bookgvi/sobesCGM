package com.example.sobes.advise;

import com.example.sobes.exceptions.SecurityException;
import com.example.sobes.service.securityService.UserManagerService;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class SecurityAdvise implements MethodBeforeAdvice {
    private final UserManagerService userManagerService;

    public SecurityAdvise(UserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        if (userManagerService.getToken() == null) throw new SecurityException("Authorization required...");
    }
}
