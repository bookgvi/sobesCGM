package com.example.sobes.advise;

import com.example.sobes.service.ApplicationStringsInfo;
import com.example.sobes.service.CurRequestStringInfo;
import com.example.sobes.service.securityService.UserManagerService;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class ConfigurationProxy {

    @Bean
    @ApplicationScope
    ApplicationStringsInfo instanceApplicationStringInfo(UserManagerService userManagerService) {
        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new LoggerAdvise());
//        pf.addAdvice(new SecurityAdvise(userManagerService));
        pf.setTarget(new ApplicationStringsInfo());
        return (ApplicationStringsInfo) pf.getProxy();
    }

    @Bean
    @RequestScope
    CurRequestStringInfo curRequestStringInfo(UserManagerService userManagerService) {
        ProxyFactory pf = new ProxyFactory();
//        pf.addAdvice(new SecurityAdvise(userManagerService));
        pf.setTarget(new CurRequestStringInfo(instanceApplicationStringInfo(userManagerService)));
        return (CurRequestStringInfo) pf.getProxy();
    }
}