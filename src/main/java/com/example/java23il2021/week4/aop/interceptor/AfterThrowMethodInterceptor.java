package com.example.java23il2021.week4.aop.interceptor;

import com.example.java23il2021.week4.aop.MethodInterceptor;
import com.example.java23il2021.week4.aop.MethodInvocation;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class AfterThrowMethodInterceptor implements MethodInterceptor{
    private Object aspectInstance;
    private Method aspectMethod;

    public AfterThrowMethodInterceptor(Object aspectInstance, Method aspectMethod) {
        this.aspectInstance = aspectInstance;
        this.aspectMethod = aspectMethod;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws InvocationTargetException, IllegalAccessException {
        //TODO
        Object obj = null;

        try {
            obj = methodInvocation.proceed();
        } catch (Exception e) {
            aspectMethod.setAccessible(true);
            aspectMethod.invoke(aspectInstance);
        }
        return obj;
    }
}
