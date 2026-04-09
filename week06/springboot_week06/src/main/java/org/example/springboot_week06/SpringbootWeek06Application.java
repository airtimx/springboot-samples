package org.example.springboot_week06;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.example.springboot_week06.mapper")
public class SpringbootWeek06Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWeek06Application.class, args);
	}

}
