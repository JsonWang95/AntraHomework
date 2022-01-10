package com.example.java23il2021.week4.aop;

public interface MethodInterceptor {
    Object invoke(MethodInvocation methodInvocation) throws Exception;
}
