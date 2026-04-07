package org.example.springbootweek04dome.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import org.example.springbootweek04dome.commom.BusinessException;
import org.example.springbootweek04dome.commom.Result;
import org.example.springbootweek04dome.entuty.Team;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/team")
@Slf4j
public class TeamController {
    @PostMapping("/add")
    public Result<String> addTeam(@Validated @RequestBody Team team, HttpServletRequest request) {
        log.info("添加团队:{}", team);
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            throw new BusinessException(401, "请先登录");
        }
        if (!"admin".equals(token)) {
            throw new BusinessException(403, "权限不足");
        }
        return Result.success();
    }
}
