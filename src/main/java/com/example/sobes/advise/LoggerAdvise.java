package com.example.sobes.advise;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.logging.Logger;

public class LoggerAdvise implements MethodInterceptor {
    Logger logger = Logger.getLogger(LoggerAdvise.class.getName());

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String methodName = invocation.getMethod().getName();
        String className = invocation.getClass().getName();
        logger.info("Advise before " + className + "#" + methodName + "();");
        Object res = invocation.proceed();
        logger.info("Advise after "  + className + "#" + methodName + "();");
        return res;
    }

}
