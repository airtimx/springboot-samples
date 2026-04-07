package org.example.springbootweek05.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootweek05.common.Result;
import org.example.springbootweek05.entity.User;
import org.example.springbootweek05.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 标记为接口层，返回JSON
@RequestMapping("/api/user") // 接口统一前缀
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping
    public Result<String> add(@RequestBody User user) {
        return userService.add(user) > 0 ? Result.success() : Result.error("添加失败");
    }

    @GetMapping("/{id}")
    public Result<User> get(@PathVariable Long id) {
        return Result.success(userService.getById(id));
    }

    @GetMapping("/list")
    public Result<List<User>> list() {
        return Result.success(userService.list());
    }

    @PutMapping
    public Result<String> update(@RequestBody User user) {
        return userService.update(user) > 0 ? Result.success() : Result.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        return userService.delete(id) > 0 ? Result.success() : Result.error("删除失败");
    }

    @GetMapping("/search")
    public Result<List<User>> search(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Integer minAge) {
        return Result.success(userService.search(username, minAge));
    }
}