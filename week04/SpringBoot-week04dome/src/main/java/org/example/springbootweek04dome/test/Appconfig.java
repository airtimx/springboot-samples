package org.example.springbootweek04dome.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 配置类必须加这个注解
@Configuration
public class Appconfig {

    // @Bean：把方法返回的对象，交给Spring管理
    @Bean
    public Car myCar() {
        // 创建对象并返回
        return new Car("特斯拉");
    }
}