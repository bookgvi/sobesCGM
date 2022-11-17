package com.example.sobes.service.securityService;

import com.example.sobes.dto.AuthInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Service
@RequestScope
public class UserLoginService {
    private final String defaultUserName = "qqq";
    private final String defaultUserPass = "qqq";
    private final UserManagerService userManagerService;

    public UserLoginService(UserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    /**
     * Simple auth method
     * @param userName - username string
     * @param password - password stream
     * @return UUID token
     * */
    public String auth(String userName, String password) {
        if (defaultUserName.equals(userName) && defaultUserPass.equals(password)) {
            userManagerService.setUserName(userName);
            userManagerService.setPassword(password);
            userManagerService.generateToken();
        } else {
            userManagerService.resetAuth();
        }
        return userManagerService.getToken();
    }

    public String auth(AuthInfo authInfo) {
        return auth(authInfo.getUserName(), authInfo.getPassword());
    }
}
