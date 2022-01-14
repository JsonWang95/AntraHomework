package com.example.java23il2021.week4.aop.interceptor;

import com.example.java23il2021.week4.aop.MethodInterceptor;
import com.example.java23il2021.week4.aop.MethodInvocation;

import java.lang.reflect.Method;

public class AfterReturnMethodInterceptor implements MethodInterceptor {

    private Object aspectInstance;
    private Method aspectMethod;

    public AfterReturnMethodInterceptor(Object aspectInstance, Method aspectMethod) {
        this.aspectInstance = aspectInstance;
        this.aspectMethod = aspectMethod;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Exception {
        //TODO
        try {
            return methodInvocation.proceed();
        } finally {
            aspectMethod.setAccessible(true);
            aspectMethod.invoke(aspectInstance);
        }
    }
}
