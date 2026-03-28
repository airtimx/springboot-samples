package org.example.springbootweek04dome.test;

// 一个普通的类
public class Car {
    private String name;

    // 构造方法
    public Car(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println(name + " 正在跑～");
    }
}