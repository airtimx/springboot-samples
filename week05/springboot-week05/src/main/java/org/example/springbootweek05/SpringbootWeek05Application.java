package org.example.springbootweek05;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("org.example.springbootweek05.mapper")
@ComponentScan("org.example")
public class SpringbootWeek05Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWeek05Application.class, args);
    }

}
