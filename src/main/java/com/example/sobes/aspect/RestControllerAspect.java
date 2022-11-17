package com.example.sobes.aspect;

import com.example.sobes.exceptions.SecurityException;
import com.example.sobes.service.securityService.UserManagerService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

@Aspect
//@Component
public class RestControllerAspect {
    private final UserManagerService userManagerService;

    public RestControllerAspect(UserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    @Around("execution(* com.example.sobes.controller.*.*(..)) && !@annotation(com.example.sobes.annotation.PermitAll)")
    public Object checkAuthorization(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Optional<Object> requestOpt = Arrays.stream(args).filter(elt -> elt instanceof HttpServletRequest).findFirst();
        HttpServletRequest request = (HttpServletRequest) requestOpt.orElse(null);
        if (request != null) {
            String tokenValue = request.getHeader("token");
            boolean isAuthorizedReqest = tokenValue != null && tokenValue.equals(userManagerService.getToken());
            if (!isAuthorizedReqest) throw  new SecurityException("Not permitted...");
        }
        return joinPoint.proceed();
    }
}
