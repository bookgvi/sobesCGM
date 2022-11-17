package com.example.sobes.controller;

import com.example.sobes.annotation.PermitAll;
import com.example.sobes.dto.AuthInfo;
import com.example.sobes.service.securityService.UserLoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/login")
public class AuthController {
    private final UserLoginService userLoginService;

    public AuthController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @PermitAll
    @PostMapping
    public ResponseEntity<?> auth(@RequestBody AuthInfo authInfo) {
        String token = userLoginService.auth(authInfo);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
