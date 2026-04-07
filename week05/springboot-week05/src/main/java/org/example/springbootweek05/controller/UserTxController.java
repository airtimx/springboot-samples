package org.example.springbootweek05.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootweek05.common.Result;
import org.example.springbootweek05.entity.User;
import org.example.springbootweek05.service.UserTxService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user/tx")
@RequiredArgsConstructor
public class UserTxController {

    private final UserTxService userTxService;

    @PostMapping("/addTwo")
    public Result<String> addTwo(@RequestBody Map<String, User> map) {
        try {
            userTxService
                    .addTwo(map.get("user1"), map.get("user2"));
            return Result.success("两个用户添加成功");
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
