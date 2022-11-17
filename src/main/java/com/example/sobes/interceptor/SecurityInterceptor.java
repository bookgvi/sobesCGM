package com.example.sobes.interceptor;

import com.example.sobes.exceptions.SecurityException;
import com.example.sobes.service.securityService.UserManagerService;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityInterceptor implements HandlerInterceptor {
    private final UserManagerService userManagerService;

    public SecurityInterceptor(UserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tokenValue = request.getHeader("token");
        boolean isAuthorizedReqest = tokenValue != null && tokenValue.equals(userManagerService.getToken());
        if (!isAuthorizedReqest) throw new SecurityException("Not permitted...");
        return true;
    }
}
