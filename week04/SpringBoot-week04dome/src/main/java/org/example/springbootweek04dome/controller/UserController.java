package org.example.springbootweek04dome.controller;

import org.example.springbootweek04dome.commom.Result;
import org.example.springbootweek04dome.entuty.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @GetMapping("/info")
    public Result<User> getUserInfo() {
         User user = new User();
         user.setUsername("zhangsan");
         user.setId(12345789987654321L);
         user.setCreateTime(LocalDateTime.now());
         return Result.success(user);
    }
}
