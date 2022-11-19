package com.example.sobes.advise.advisors;

import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class App {
    public static void main(String[] args) {
        Pointcut sp = new SimpleStaticAdvisor();
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(sp, new SimpleAdvise());
        ProxyFactory pf = new ProxyFactory();
        pf.addAdvisor(advisor);
        pf.setTarget(new SimpleImpl());
        pf.setInterfaces(Simple.class);
        Simple proxy = (Simple) pf.getProxy();
        proxy.foo();
    }
}
