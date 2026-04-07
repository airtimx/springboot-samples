package org.example.springbootweek05.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.example.springbootweek05.common.Result;
import org.example.springbootweek05.entity.User;
import org.example.springbootweek05.service.UserMPService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/mp")
@RequiredArgsConstructor
public class UserMPController {

    private final UserMPService userMPService;

    @GetMapping("/page")
    public Result<Page<User>> page(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String username) {
        return Result.success(userMPService.page(username, pageNum, pageSize));
    }

    @PostMapping
    public Result<String> add(@RequestBody User user) {
        return userMPService.add(user) > 0 ? Result.success() : Result.error("新增失败");
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        return userMPService.delete(id) > 0 ? Result.success() : Result.error("删除失败");
    }
}
