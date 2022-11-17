package com.example.sobes.interceptor;

import com.example.sobes.service.securityService.UserManagerService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class ConfigurationInterceptor implements WebMvcConfigurer {
    private final UserManagerService userManagerService;

    public ConfigurationInterceptor(UserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityInterceptor(userManagerService))
                .excludePathPatterns("/login")
                .addPathPatterns("/**");
    }
}
