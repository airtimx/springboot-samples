package org.example.springbootweek04dome.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class test {
    public static void main(String[] args) {
        // 加载配置类
        ApplicationContext context = new AnnotationConfigApplicationContext(Appconfig.class);

        // 从Spring容器中拿@Bean创建的对象
        Car car = context.getBean(Car.class);

        // 使用对象
        car.run();
    }
}