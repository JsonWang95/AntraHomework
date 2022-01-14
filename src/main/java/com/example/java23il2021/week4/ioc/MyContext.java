package com.example.java23il2021.week4.ioc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyContext {
    private static final Map<String, Object> objMap = new HashMap<>();

    private static List<Class<?>> scan() throws Exception {
        return Arrays.asList(EmployeeService1.class, EmployeeService2.class, TmpService.class);
    }

    public static void init() throws Exception {
        List<Class<?>> classes = scan();
        for(Class<?> clazz: classes) {
            String name = clazz.getSimpleName();
            Object instance = clazz.getDeclaredConstructor().newInstance();
            objMap.put(name, instance);
        }
        for(Object instance: objMap.values()) {
            Class<?> clazz = instance.getClass();
            for(Field field: clazz.getDeclaredFields()) {
                for(Annotation annotation: field.getDeclaredAnnotations()) {
                    if(annotation.annotationType() == Autowired.class) {
                        String name = field.getType().getSimpleName();
                        field.setAccessible(true);
                        field.set(instance, objMap.get(name));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        init();
        System.out.println(objMap);
    }

}

class TmpService {

    private final EmployeeService1 ss1;
    private final EmployeeService2 ss2;

    @Autowired
    public TmpService(EmployeeService1 ss1, EmployeeService2 ss2) {
        this.ss1 = ss1;
        this.ss2 = ss2;
    }

    public TmpService() {
        ss1 = null;
        ss2 = null;
    }

    @Override
    public String toString() {
        return "TmpService{" +
                "ss1=" + ss1 +
                ", ss2=" + ss2 +
                '}';
    }
}


class EmployeeService1 {
}

class EmployeeService2 {
}

