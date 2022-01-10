package com.example.java23il2021.week4.aop;

import com.example.java23il2021.week4.aop.annotation.After;
import com.example.java23il2021.week4.aop.annotation.Before;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyExample {
    public static void main(String[] args) throws Exception{
        Calculator c1 = new CalculatorImpl1();
        Calculator proxy = (Calculator) Proxy.newProxyInstance(
                c1.getClass().getClassLoader(),
                new Class[]{Calculator.class},
                new JdkReflectiveInvocationHandler(c1, new CalculatorAspect())
        );
        proxy.run();
    }
}

class MyInvocationHandler implements InvocationHandler {
    private Object realInstance;

    public MyInvocationHandler(Object realInstance) {
        this.realInstance = realInstance;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("this is our proxy");
        return method.invoke(realInstance);
    }
}

interface Calculator {
    void run();
    void get();
}

class CalculatorImpl1 implements Calculator {
    @Override
    public void run() {
        System.out.println("this is our calculator run");
    }

    @Override
    public void get() {
        System.out.println("this is our calculator get");
    }
}

class CalculatorAspect {

    @After
    public void afterLogic1() {
        System.out.println("this is after -- number 1");
    }

    @After
    public void afterLogic2() {
        System.out.println("this is after -- number 2");
    }

    @Before
    public void beforeLogic1() {
        System.out.println("this is before -- number 1");
    }

    @Before
    public void beforeLogic2() {
        System.out.println("this is before -- number 2");
    }
}