package com.example.java23il2021.week4.aop;

import com.example.java23il2021.week4.aop.annotation.After;
import com.example.java23il2021.week4.aop.annotation.Before;
import com.example.java23il2021.week4.aop.annotation.AfterReturn;
import com.example.java23il2021.week4.aop.annotation.AfterThrow;

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
    @Before
    public void beforeLogic() {
        System.out.println("this is before");
    }

    @After
    public void afterLogic() {
        System.out.println("this is after");
    }

    @AfterReturn
    public void afterReturnLogic() { System.out.println("this is after return");}

    @AfterThrow
    public void afterThrowLogic() { System.out.println("this is after throw");}
}