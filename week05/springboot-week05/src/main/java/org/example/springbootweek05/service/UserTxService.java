package org.example.springbootweek05.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootweek05.entity.User;
import org.example.springbootweek05.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class UserTxService {
    private final UserMapper userMapper;

    @Transactional
    public void addTwo(User user1, User user2) {
        userMapper.insert(user1);
        if (user2.getUsername() == null || user2.getUsername().isBlank()) {
            throw new RuntimeException("用户2姓名不能为空，事务回滚");
        }
        userMapper.insert(user2);
    }
}
