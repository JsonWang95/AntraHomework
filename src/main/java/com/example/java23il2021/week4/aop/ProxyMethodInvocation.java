package com.example.java23il2021.week4.aop;

import java.lang.reflect.Method;
import java.util.List;

public class ProxyMethodInvocation implements MethodInvocation{

    private List<MethodInterceptor> methodInterceptorList;
    private Object realInstance;
    private Method method;
    private int idx;

    public ProxyMethodInvocation(List<MethodInterceptor> methodInterceptorList, Object realInstance, Method method) {
        this.methodInterceptorList = methodInterceptorList;
        this.realInstance = realInstance;
        this.method = method;
    }

    @Override
    public Object proceed() throws Exception {
        if(idx < methodInterceptorList.size()) {
            MethodInterceptor mi = methodInterceptorList.get(idx);
            idx++;
            return mi.invoke(this);
        } else {
            return method.invoke(realInstance);
        }
    }
}

/**
 *  interceptors = [After1, After2, Before1, Before2]
 *  return pmi.proceed() index = 0 {
 *      After1.invoke(pmi) {
 *          res1 = pmi.proceed() index = 1 {
 *              After2.invoke(pmi) {
 *                  res2 = pmi.proceed() index = 2 {
 *                      Before1.invoke(pmi) {
 *                          execute before1 logic
 *                          return pmi.proceed() index = 3 {
 *                              Before2.invoke(pmi) {
 *                                  execute before2 logic
 *                                  return pmi.proceed() index = 4 {
 *                                      execute real logic
 *                                  }
 *                              }
 *                          }
 *                      }
 *                  }
 *                  execute after2 logic
 *                  return res2
 *              }
 *          }
 *          execute after1 logic
 *          return res1
 *      }
 *  }
 */








/**
 *  Before1, After1, Before2
 *  ProxyMethodInvocation instance1, index = 0
 *      Before1.invoke(instance1)
 *          execute before1 aspect method
 *          return instance1.proceed(), index = 1
 *              After1.invoke(instance1)
 *                  res = instance1.proceed(), index = 2
 *                          Before2.invoke(instance1)
 *                          execute before2 aspect method
 *                          return instance1.proceed(), index = 3
 *                                 invoke real method
 *                  execute after1 aspect method
 *                  return res
 *
 */


/**
 *    homework
 *      1. IOC => implement constructor injection
 *      2. AOP => @AfterReturn, @AfterThrow
 *                *@Around
 *
 *    due Thursday 8pm CDT
 */
