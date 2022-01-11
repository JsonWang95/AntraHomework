package com.example.java23il2021.week4.day16;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Day16Application {

    private static Day16EmployeeService ds1;
    private static Day16EmployeeService ds2;
    private static Day16EmployeeService day16EmployeeServiceImpl2;

    @Autowired
    public Day16Application(
            @Qualifier("day16EmployeeServiceImpl2") Day16EmployeeService ds1,
            @Qualifier("day16EmployeeServiceImpl2") Day16EmployeeService ds2,
            Day16EmployeeService day16EmployeeServiceImpl2
    ) {
        Day16Application.ds1 = ds1;
        Day16Application.ds2 = ds2;
        Day16Application.day16EmployeeServiceImpl2 = day16EmployeeServiceImpl2;
    }

    public static void main(String[] args) {
        SpringApplication.run(Day16Application.class, args);
        System.out.println(ds1 == ds2);
    }
}
@Component
interface Day16EmployeeService {}
@Component
class Day16EmployeeServiceImpl1 implements Day16EmployeeService{}
@Component
@Scope("prototype")
class Day16EmployeeServiceImpl2 implements Day16EmployeeService{}



/**
 *  1. start application context(IOC)
 *  2. scan "spring.factories"
 *          @Bean / @Component / @Service / @Repository / @Controller
 *  3. @Autowired : setter / constructor / field
 *     By Type / By Name(reference name / @Qualifier)
 *     bean scope: singleton(default) / prototype / request / session / global session
 */

/**
 *  server vs client
 *
 *  request => tomcat(war) => servlet(path = /student =>  StudentClass)
 *  1. build connection
 *  2. assign thread to this request
 *  3. give response to user
 *  4. return thread to thread pool
 */

/**
 *  Spring Boot
 *      1. auto configuration
 *      2. application.properties
 *      3. spring boot starter + jar
 *      4. embedded tomcat
 *      5. actuator
 *
 *
 *  ip => public ip + private ip(NAT)
 *  Application
 *  Presentation
 *  Session
 *  Transport
 *  Network
 *  Data link
 *  Physical
 *
 *  http
 *  https
 *
 *  Tomorrow 2:30pm cdt
 */