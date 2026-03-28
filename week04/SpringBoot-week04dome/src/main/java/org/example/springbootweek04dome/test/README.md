# @Bean 注解学习文档

## 一、@Bean 注解的作用

`@Bean` 是 Spring 框架的核心注解，用于**将方法返回的对象交给 Spring 容器管理**。

### 核心作用
- 将方法的返回值注册为 Spring 容器中的 Bean
- Bean 名称默认使用方法名
- 用于配置第三方类或需要手动创建的对象

---

## 二、代码示例

### 1. 普通类 (Car.java)
```java
package org.example.springbootweek04dome.test;

public class Car {
    private String name;

    public Car(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println(name + " 正在跑～");
    }
}
```

### 2. 配置类 (Appconfig.java)
```java
package org.example.springbootweek04dome.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // 标识这是一个配置类
public class Appconfig {

    // @Bean：把方法返回的对象，交给Spring管理
    @Bean
    public Car myCar() {
        return new Car("特斯拉");
    }
}
```

### 3. 测试类 (test.java)
```java
package org.example.springbootweek04dome.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class test {
    public static void main(String[] args) {
        // 加载配置类
        ApplicationContext context = new AnnotationConfigApplicationContext(Appconfig.class);

        // 从Spring容器中获取@Bean创建的对象
        Car car = context.getBean(Car.class);

        // 使用对象
        car.run();
    }
}
```

---

## 三、运行结果

```
特斯拉 正在跑～
```

---

## 四、总结

| 注解 | 作用 |
|------|------|
| `@Configuration` | 标识配置类，Spring 会扫描此类 |
| `@Bean` | 将方法返回值注册为 Spring 容器中的 Bean |

### 使用场景
1. **第三方类** - 无法修改源码的类
2. **需要自定义创建逻辑**的对象
3. **替代 XML 配置**中的 `<bean>` 标签
