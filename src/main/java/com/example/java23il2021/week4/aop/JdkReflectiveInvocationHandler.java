package com.example.java23il2021.week4.aop;

import com.example.java23il2021.week4.aop.annotation.After;
import com.example.java23il2021.week4.aop.annotation.Before;
import com.example.java23il2021.week4.aop.interceptor.AfterMethodInterceptor;
import com.example.java23il2021.week4.aop.interceptor.BeforeMethodInterceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class JdkReflectiveInvocationHandler implements InvocationHandler {
    private Object realInstance;
    private Object aspectInstance;

    public JdkReflectiveInvocationHandler(Object realInstance, Object aspectInstance) {
        this.realInstance = realInstance;
        this.aspectInstance = aspectInstance;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> clazz = aspectInstance.getClass();
        List<MethodInterceptor> methodInterceptorList = new ArrayList<>();
        for(Method aspectMethod: clazz.getDeclaredMethods()) {
            for(Annotation annotation: aspectMethod.getDeclaredAnnotations()) {
                if(annotation.annotationType() == Before.class) {
                    methodInterceptorList.add(new BeforeMethodInterceptor(aspectInstance, aspectMethod));
                } else if(annotation.annotationType() == After.class) {
                    methodInterceptorList.add(new AfterMethodInterceptor(aspectInstance, aspectMethod));
                }
            }
        }
        ProxyMethodInvocation proxyMethodInvocation = new ProxyMethodInvocation(
                methodInterceptorList,
                realInstance,
                method
        );

        return proxyMethodInvocation.proceed();
    }
}
