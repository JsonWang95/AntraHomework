package com.example.java23il2021.week4.aop.interceptor;

import com.example.java23il2021.week4.aop.MethodInterceptor;
import com.example.java23il2021.week4.aop.MethodInvocation;

import java.lang.reflect.Method;

public class AfterMethodInterceptor implements MethodInterceptor {
    private Object aspectInstance;
    private Method aspectMethod;

    public AfterMethodInterceptor(Object aspectInstance, Method aspectMethod) {
        this.aspectInstance = aspectInstance;
        this.aspectMethod = aspectMethod;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Exception {
        aspectMethod.setAccessible(true);
        Object res = methodInvocation.proceed();
        aspectMethod.invoke(aspectInstance);
        return res;
    }
}
